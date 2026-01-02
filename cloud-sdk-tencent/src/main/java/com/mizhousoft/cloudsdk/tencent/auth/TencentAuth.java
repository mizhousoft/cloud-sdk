package com.mizhousoft.cloudsdk.tencent.auth;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.core.HttpRequest;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * TODO
 *
 * @version
 */
public class TencentAuth
{
	public static final String REQ_POST = "POST";

	public static final String REQ_GET = "GET";

	public static Map<String, String> doRequestWithTC3(HttpRequest request, ClientProfile profile, Credential credential)
	        throws CloudSDKException
	{
		String endpoint = profile.getEndpoint();

		String httpRequestMethod = request.getHttpMethod().toString();
		if (httpRequestMethod == null)
		{
			throw new CloudSDKException("Request method should not be null, can only be GET or POST");
		}
		String contentType = "application/x-www-form-urlencoded";
		byte[] requestPayload = "".getBytes(StandardCharsets.UTF_8);

		String[] binaryParams = new String[0];
		if (binaryParams.length > 0)
		{

		}
		else if (httpRequestMethod.equals(REQ_POST))
		{
			requestPayload = request.getStringBody().getBytes(StandardCharsets.UTF_8);
			// okhttp always set charset even we don't specify it,
			// to ensure signature be correct, we have to set it here as well.
			contentType = "application/json; charset=utf-8";
		}
		// Construct the canonical request for signature calculation.
		String canonicalUri = "/";
		String canonicalQueryString = request.getQueryParamsString();
		String canonicalHeaders = "content-type:" + contentType + "\nhost:" + endpoint + "\n";
		String signedHeaders = "content-type;host";

		String hashedRequestPayload = "";
		if (request.isUnsignedPayload())
		{
			hashedRequestPayload = Sign.sha256Hex("UNSIGNED-PAYLOAD".getBytes(StandardCharsets.UTF_8));
		}
		else
		{
			hashedRequestPayload = Sign.sha256Hex(requestPayload);
		}
		String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n" + canonicalHeaders + "\n"
		        + signedHeaders + "\n" + hashedRequestPayload;

		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));
		String service = endpoint.split("\\.")[0];
		String credentialScope = date + "/" + service + "/" + "tc3_request";
		String hashedCanonicalRequest = Sign.sha256Hex(canonicalRequest.getBytes(StandardCharsets.UTF_8));
		String stringToSign = "TC3-HMAC-SHA256\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;
		String authorization = "";

		if (request.isSkipRequestSign())
		{
			authorization = "SKIP";
		}
		else
		{
			String secretId = credential.getAccessKey();
			String secretKey = credential.getSecretKey();
			byte[] secretDate = Sign.hmac256(("TC3" + secretKey).getBytes(StandardCharsets.UTF_8), date);
			byte[] secretService = Sign.hmac256(secretDate, service);
			byte[] secretSigning = Sign.hmac256(secretService, "tc3_request");
			String signature = HexUtils.encodeHexString(Sign.hmac256(secretSigning, stringToSign), true);
			authorization = "TC3-HMAC-SHA256 " + "Credential=" + secretId + "/" + credentialScope + ", " + "SignedHeaders=" + signedHeaders
			        + ", " + "Signature=" + signature;
		}

		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("Content-Type", contentType);
		headerMap.put("Authorization", authorization);
		headerMap.put("X-TC-Action", request.getName());
		headerMap.put("X-TC-Timestamp", timestamp);
		headerMap.put("X-TC-Version", profile.getVersion());
		headerMap.put("X-TC-RequestClient", "SDK_JAVA_BAREBONE");
		if (null != request.getHeaders())
		{
			for (Map.Entry<String, String> entry : request.getHeaders().entrySet())
			{
				headerMap.put(entry.getKey(), entry.getValue());
			}
		}

		if (null != profile.getRegion())
		{
			headerMap.put("X-TC-Region", profile.getRegion());
		}
		if (request.isUnsignedPayload())
		{
			headerMap.put("X-TC-Content-SHA256", "UNSIGNED-PAYLOAD");
		}
		if (null != profile.getLanguage())
		{
			headerMap.put("X-TC-Language", profile.getLanguage().getValue());
		}

		return headerMap;
	}

}
