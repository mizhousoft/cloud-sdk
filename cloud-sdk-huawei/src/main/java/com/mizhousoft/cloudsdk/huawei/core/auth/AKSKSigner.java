package com.mizhousoft.cloudsdk.huawei.core.auth;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.SimpleTimeZone;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.mizhousoft.cloudsdk.huawei.core.http.HeaderConstants;
import com.mizhousoft.cloudsdk.huawei.core.impl.DefaultHttpRequest;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * 签名
 *
 * @version
 */
public class AKSKSigner
{
	private static final AKSKSigner SINGLETON = new AKSKSigner();

	protected SHA256Hasher hasher = new SHA256Hasher();

	protected String algorithm = HeaderConstants.SDK_HMAC_SHA256;

	protected String contentHeader = HeaderConstants.X_SDK_CONTENT_SHA256;

	protected String emptyHash = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

	protected AKSKSigner()
	{
	}

	public static AKSKSigner getInstance()
	{
		return SINGLETON;
	}

	public Map<String, String> sign(DefaultHttpRequest request, ICredential credentials)
	{
		// ************* TASK 1: CONSTRUCT CANONICAL REQUEST *************
		HashMap<String, String> authenticationHeaders = new HashMap<>();

		// Step 1: add basic headers required by V4
		URL url = request.getUrl();

		// Step 1.2: Add X-Sdk-Date
		String dateTimeStamp = extractTimeStamp(request, authenticationHeaders);
		// Step 1.3 combine all headers
		Map<String, String> allHeaders = combineAllHeaders(request, authenticationHeaders);
		// Step 2: Create Canonical URI -- the part of the URI from domain to query
		String canonicalUri = buildCanonicalUri(url);
		// Step 3: Create the canonical query string. In this example (a GET request),
		// request parameters are in the query string. Query string values must
		// be URL-encoded (space=%20). The parameters must be sorted by name.
		// For this example, the query string is pre-formatted in the request_parameters variable.
		String query = url.getQuery();
		Map<String, List<String>> parameters = request.getQueryParams();
		String canonicalQueryString = buildCanonicalQueryString(query, parameters);
		// Step 4: Create the list of signed headers. This lists the headers
		// in the canonical_headers list, delimited with ";" and in alpha order.
		// Note: The request can include any headers; canonical_headers and
		// signed_headers lists those that you want to be included in the
		// hash of the request. "Host" and "x-sdk-date" are always required.
		// In V4 signer, we only use required header - host & x-sdk-date.
		String signedHeaderNames = String.join(";", allHeaders.keySet());
		// Step 5: Create the canonical headers and signed headers. Header names
		// and value must be trimmed and lower-case, and sorted in ASCII order.
		// Note that there is a trailing \n.
		String canonicalHeaders = buildCanonicalHeaders(allHeaders);
		// Step 6: Create payload hash (hash of the request body content). For GET
		// requests, the payload is an empty string ("").
		String payloadHash = buildPayloadHash(request);
		// Step 7: Combine elements to create canonical request
		String canonicalRequest = buildCanonicalRequest(request.getHttpMethod().name(), canonicalUri, canonicalQueryString, canonicalHeaders,
		        signedHeaderNames, payloadHash);
		String canonicalRequestHash = hasher.hashHexString(canonicalRequest.getBytes(StandardCharsets.UTF_8));
		// ************* TASK 2: CREATE THE STRING TO SIGN*************
		// Match the algorithm to the hashing algorithm you use, either SHA-1 or SHA-256
		// (recommended)
		String stringToSign = buildStringToSign(algorithm, dateTimeStamp, canonicalRequestHash);
		// ************* TASK 3: CALCULATE THE SIGNATURE *************
		// Create the signing key using the function defined above.
		ISigningKey signingKey = getSigningKey(credentials);
		String signature = buildSignature(stringToSign, signingKey);
		// ************* TASK 4: ADD SIGNING INFORMATION TO THE REQUEST *************
		// The signing information can be either in a query string value or in
		// a header named Authorization. This code shows how to use a header.
		// Create authorization header and add to request headers
		String authorization = String.format(Locale.US, "%s Access=%s, SignedHeaders=%s, Signature=%s", algorithm,
		        credentials.getAccessKey(), signedHeaderNames, signature);
		authenticationHeaders.put(HeaderConstants.AUTHORIZATION, authorization);
		return authenticationHeaders;
	}

	public ISigningKey getSigningKey(ICredential credentials)
	{
		return new HmacSigningKey(hasher, credentials.getSecretKey().getBytes(StandardCharsets.UTF_8));
	}

	protected String extractTimeStamp(DefaultHttpRequest request, Map<String, String> authenticationHeaders)
	{
		if (request.containHeader(HeaderConstants.X_SDK_DATE))
		{
			return request.getFirstHeader(HeaderConstants.X_SDK_DATE);
		}

		SimpleDateFormat isoDateFormat = new SimpleDateFormat(HeaderConstants.ISO_8601_BASIC_FORMAT, Locale.US);
		isoDateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
		String dateTimeStamp = isoDateFormat.format(new Date());
		authenticationHeaders.put(HeaderConstants.X_SDK_DATE, dateTimeStamp);
		return dateTimeStamp;
	}

	protected Map<String, String> combineAllHeaders(DefaultHttpRequest request, Map<String, String> authenticationHeaders)
	{
		Map<String, String> allHeaders = new TreeMap<>();

		// filter out content-type header
		allHeaders.putAll(request.getHeaders()
		        .entrySet()
		        .stream()
		        .filter(entry -> !(entry.getKey().equalsIgnoreCase(HeaderConstants.CONTENT_TYPE) || entry.getKey().contains("_")))
		        .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(Locale.US), entry -> entry.getValue().get(0))));
		allHeaders.putAll(authenticationHeaders.entrySet()
		        .stream()
		        .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(Locale.US), Map.Entry::getValue)));
		return allHeaders;
	}

	protected String buildCanonicalUri(URL url)
	{
		String pathOld = url.getPath();
		if (pathOld.equals("/"))
		{
			return pathOld;
		}

		StringBuilder canonicalUri = new StringBuilder();
		String[] split = pathOld.split("/");
		for (String urlSplit : split)
		{
			canonicalUri.append(SignUtils.urlEncode(urlSplit, false)).append("/");
		}
		return canonicalUri.toString();
	}

	protected String buildCanonicalQueryString(String query, Map<String, List<String>> parameters)
	{
		SortedMap<String, List<String>> sorted = SignUtils.convertQuery2SortedMap(query);

		if (parameters == null || parameters.isEmpty())
		{
			return "";
		}

		Iterator<Map.Entry<String, List<String>>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry<String, List<String>> pair = iterator.next();
			String key = pair.getKey();
			List<String> values = pair.getValue();
			List<String> escapedValues = new ArrayList<>();
			for (String value : values)
			{
				escapedValues.add(SignUtils.urlEncode(value, false));
			}
			sorted.put(SignUtils.urlEncode(key, false), escapedValues);
		}

		return SignUtils.convertSortedMap2QueryString(sorted);
	}

	/**
	 * Create the canonical headers and signed headers. Header names
	 * and value must be trimmed and lowercase, and sorted in ASCII order.
	 * Note that there is a trailing \n.
	 *
	 * @param headers
	 * @return
	 */
	protected String buildCanonicalHeaders(Map<String, String> headers)
	{
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : headers.entrySet())
		{
			sb.append(entry.getKey().toLowerCase(Locale.US)).append(":").append(entry.getValue()).append(HeaderConstants.LINE_SEPARATOR);
		}
		return sb.toString();
	}

	/**
	 * @param request
	 * @return
	 */
	protected String buildPayloadHash(DefaultHttpRequest request)
	{
		if (request.containHeader(contentHeader))
		{
			return request.getFirstHeader(contentHeader);
		}
		if (Objects.nonNull(request.getStringBody()) && !request.getStringBody().isEmpty())
		{
			return hasher.hashHexString(request.getStringBody().getBytes(StandardCharsets.UTF_8));
		}
		if (Objects.nonNull(request.getStreamBody()))
		{
			try
			{
				int len = request.getStreamBody().available();
				byte[] body = new byte[len];
				for (int i = 0; i < len; i++)
				{
					int byteRead = request.getStreamBody().read();
					body[i] = (byte) byteRead;
				}
				request.getStreamBody().reset();
				return hasher.hashHexString(body);
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		return emptyHash;
	}

	/**
	 * @param segments param1 method
	 *            param2 canonicalURI
	 *            param3 canonicalQueryString
	 *            param4 canonicalHeaders
	 *            param5 signedHeaderNames
	 *            param6 payloadHash
	 * @return
	 */
	protected String buildCanonicalRequest(String... segments)
	{
		return String.join(HeaderConstants.LINE_SEPARATOR, segments);
	}

	/**
	 * @param segments param1 sdkSigningAlgorithm
	 *            param2 dateTimeStamp
	 *            param3 credentialScope
	 *            param4 canonicalRequestHash
	 * @return
	 */
	protected String buildStringToSign(String... segments)
	{
		return String.join(HeaderConstants.LINE_SEPARATOR, segments);
	}

	protected String buildSignature(String stringToSign, ISigningKey signingKey)
	{
		byte[] signed = signingKey.sign(stringToSign.getBytes(StandardCharsets.UTF_8));

		return HexUtils.encodeHexString(signed, true);
	}
}
