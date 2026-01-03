package com.mizhousoft.cloudsdk.tencent.sts.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.common.APIResponse;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.sts.StsClient;
import com.mizhousoft.cloudsdk.tencent.sts.request.GetFederationTokenRequest;
import com.mizhousoft.cloudsdk.tencent.sts.response.Credentials;
import com.mizhousoft.cloudsdk.tencent.sts.response.GetFederationTokenResponse;

import kong.unirest.core.HttpMethod;
import tools.jackson.core.type.TypeReference;

/**
 * StsClient
 *
 * @version
 */
public class DefaultStsClient extends AbstractClient implements StsClient
{
	private static final String ENDPOINT = "sts.tencentcloudapi.com";

	private static final String API_VERSION = "2018-08-13";

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 */
	public DefaultStsClient(RegionEnum region, Credential credential)
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
	public DefaultStsClient(RegionEnum region, Credential credential, ClientProfile profile)
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
	public DefaultStsClient(String endpoint, String apiVersion, RegionEnum region, Credential credential, ClientProfile profile)
	{
		super(endpoint, apiVersion, region, credential, profile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempCredential getFederationToken(GetFederationTokenRequest request) throws CloudSDKException
	{
		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("GetFederationToken")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(request)
		        .build();

		Map<String, String> headerMap = buildSignHeader(httpRequest, profile, credential);

		GetFederationTokenResponse response = executeRequest(httpRequest, headerMap,
		        new TypeReference<APIResponse<GetFederationTokenResponse>>()
		        {
		        });

		Credentials credential = response.getCredentials();

		TempCredential tempCredential = new TempCredential();
		tempCredential.setSecretId(credential.getTmpSecretId());
		tempCredential.setSecretKey(credential.getTmpSecretKey());
		tempCredential.setToken(credential.getToken());
		tempCredential.setExpiredTime(response.getExpiredTime());

		return tempCredential;
	}

}
