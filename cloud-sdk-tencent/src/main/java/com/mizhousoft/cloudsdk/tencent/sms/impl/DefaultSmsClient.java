package com.mizhousoft.cloudsdk.tencent.sms.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.auth.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.auth.Credential;
import com.mizhousoft.cloudsdk.tencent.auth.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.TencentResponse;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.sms.SmsClient;
import com.mizhousoft.cloudsdk.tencent.sms.request.SmsPackageStatisticsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.response.SmsPackagesStatisticsResponse;

import kong.unirest.core.HttpMethod;
import tools.jackson.core.type.TypeReference;

/**
 * 短信客户端
 *
 * @version
 */
public class DefaultSmsClient extends AbstractClient implements SmsClient
{
	private static String ENDPOINT = "sms.tencentcloudapi.com";

	private static String API_VERSION = "2021-01-11";

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 */
	public DefaultSmsClient(RegionEnum region, Credential credential)
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
	public DefaultSmsClient(RegionEnum region, Credential credential, ClientProfile profile)
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
	public DefaultSmsClient(String endpoint, String apiVersion, RegionEnum region, Credential credential, ClientProfile profile)
	{
		super(endpoint, apiVersion, region, credential, profile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsPackagesStatisticsResponse querySmsPackageStatistics(SmsPackageStatisticsRequest request) throws CloudSDKException
	{
		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("SmsPackagesStatistics")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(request)
		        .build();

		Map<String, String> headerMap = doRequestWithTC3(httpRequest, profile, credential);

		SmsPackagesStatisticsResponse response = executeRequest(httpRequest, headerMap,
		        new TypeReference<TencentResponse<SmsPackagesStatisticsResponse>>()
		        {
		        });

		return response;
	}
}
