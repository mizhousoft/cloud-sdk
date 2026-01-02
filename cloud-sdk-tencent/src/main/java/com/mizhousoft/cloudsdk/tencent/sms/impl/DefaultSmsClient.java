package com.mizhousoft.cloudsdk.tencent.sms.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.auth.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.auth.Credential;
import com.mizhousoft.cloudsdk.tencent.auth.TencentAuth;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;
import com.mizhousoft.cloudsdk.tencent.core.TencentResponse;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.sms.SmsClient;
import com.mizhousoft.cloudsdk.tencent.sms.request.SmsPackageStatisticsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.response.SmsPackagesStatisticsResponse;
import com.mizhousoft.commons.json.JSONException;
import com.mizhousoft.commons.json.JSONUtils;

import kong.unirest.core.HttpMethod;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.HttpStatus;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import tools.jackson.core.type.TypeReference;

/**
 * 短信客户端
 *
 * @version
 */
public class DefaultSmsClient implements SmsClient
{
	/**
	 * Credential
	 */
	private Credential credential;

	/**
	 * Profile
	 */
	private ClientProfile profile;

	/**
	 * 构造函数
	 *
	 * @param credential
	 * @param profile
	 */
	public DefaultSmsClient(Credential credential, ClientProfile profile)
	{
		super();
		this.credential = credential;
		this.profile = profile;
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
		        .endpoint(profile.getEndpoint())
		        .path("")
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(request)
		        .build();

		Map<String, String> headerMap = TencentAuth.doRequestWithTC3(httpRequest, profile, credential);

		SmsPackagesStatisticsResponse response = executeRequest(httpRequest, headerMap,
		        new TypeReference<TencentResponse<SmsPackagesStatisticsResponse>>()
		        {
		        });

		return response;
	}

	private <T extends GeneralResponse> T executeRequest(DefaultHttpRequest request, Map<String, String> headers,
	        TypeReference<TencentResponse<T>> valueTypeRef) throws CloudSDKException
	{
		try
		{
			HttpResponse<String> response = null;

			if (HttpMethod.POST.equals(request.getHttpMethod()))
			{
				response = Unirest.post(request.getUrl().toString()).headers(headers).body(request.getStringBody()).asString();
			}
			else
			{
				response = Unirest.get(request.getUrl().toString()).headers(headers).asString();
			}

			if (response.getStatus() == HttpStatus.OK)
			{
				try
				{
					TencentResponse<T> respBody = JSONUtils.parseWithTypeRef(response.getBody(), valueTypeRef);

					return respBody.getResponse();
				}
				catch (JSONException e)
				{
					throw new CloudSDKException("HTTP request execution failed", e);
				}
			}
			else
			{
				throw new CloudSDKException("Request failed, status is " + response.getStatus() + "，response body: " + response.getBody());
			}
		}
		catch (UnirestException e)
		{
			throw new CloudSDKException("HTTP request execution failed", e);
		}
	}
}
