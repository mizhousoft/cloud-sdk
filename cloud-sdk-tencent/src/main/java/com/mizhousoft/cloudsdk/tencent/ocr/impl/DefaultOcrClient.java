package com.mizhousoft.cloudsdk.tencent.ocr.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;
import com.mizhousoft.cloudsdk.tencent.auth.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.auth.Credential;
import com.mizhousoft.cloudsdk.tencent.auth.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.TencentResponse;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.ocr.OcrClient;
import com.mizhousoft.cloudsdk.tencent.ocr.request.VinOCRRequest;
import com.mizhousoft.cloudsdk.tencent.ocr.response.VinOCRResponse;
import com.mizhousoft.cloudsdk.tencent.sts.StsClient;
import com.mizhousoft.cloudsdk.tencent.sts.impl.DefaultStsClient;
import com.mizhousoft.cloudsdk.tencent.sts.request.GetFederationTokenRequest;

import kong.unirest.core.HttpMethod;
import tools.jackson.core.type.TypeReference;

/**
 * OCR客户端
 *
 * @version
 */
public class DefaultOcrClient extends AbstractClient implements OcrClient
{
	private static final String ENDPOINT = "ocr.tencentcloudapi.com";

	private static final String API_VERSION = "2018-11-19";

	/**
	 * StsClient
	 */
	private StsClient stsClient;

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 */
	public DefaultOcrClient(RegionEnum region, Credential credential)
	{
		this(ENDPOINT, API_VERSION, region, credential, new ClientProfile());
	}

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 * @param profile
	 */
	public DefaultOcrClient(RegionEnum region, Credential credential, ClientProfile profile)
	{
		this(ENDPOINT, API_VERSION, region, credential, profile);
	}

	/**
	 * 构造函数
	 *
	 * @param endpoint
	 * @param apiVersion
	 * @param region
	 * @param credential
	 * @param profile
	 */
	public DefaultOcrClient(String endpoint, String apiVersion, RegionEnum region, Credential credential, ClientProfile profile)
	{
		super(endpoint, apiVersion, region, credential, profile);

		stsClient = new DefaultStsClient(region, credential, profile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempCredential genTempCredential(long durationSecond) throws CloudSDKException
	{
		GetFederationTokenRequest request = new GetFederationTokenRequest();
		request.setName("ocr");
		request.setDurationSeconds(durationSecond);
		request.setPolicy("{\"version\": \"2.0\", \"statement\": [{\"action\": [\"ocr:*\"], \"resource\": \"*\", \"effect\": \"allow\"}]}");

		TempCredential tempCredential = stsClient.getFederationToken(request);

		return tempCredential;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String imageBase64OCR(String imageBase64) throws CloudSDKException
	{
		VinOCRRequest ocrRequest = new VinOCRRequest();
		ocrRequest.setImageBase64(imageBase64);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("VinOCR")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(ocrRequest)
		        .build();

		Map<String, String> headerMap = doRequestWithTC3(httpRequest, profile, credential);

		VinOCRResponse response = executeRequest(httpRequest, headerMap, new TypeReference<TencentResponse<VinOCRResponse>>()
		{
		});

		return response.getVin();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String imageUrlOCR(String imageUrl) throws CloudSDKException
	{
		VinOCRRequest ocrRequest = new VinOCRRequest();
		ocrRequest.setImageUrl(imageUrl);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("VinOCR")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(ocrRequest)
		        .build();

		Map<String, String> headerMap = doRequestWithTC3(httpRequest, profile, credential);

		VinOCRResponse response = executeRequest(httpRequest, headerMap, new TypeReference<TencentResponse<VinOCRResponse>>()
		{
		});

		return response.getVin();
	}

}
