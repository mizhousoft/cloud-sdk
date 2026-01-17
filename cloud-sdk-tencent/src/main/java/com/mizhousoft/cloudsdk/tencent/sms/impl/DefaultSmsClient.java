package com.mizhousoft.cloudsdk.tencent.sms.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.sms2.SmsException;
import com.mizhousoft.cloudsdk.tencent.common.APIResponse;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.sms.SmsClient;
import com.mizhousoft.cloudsdk.tencent.sms.request.SendSmsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.request.SmsPackageStatisticsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.response.SendSmsResponse;
import com.mizhousoft.cloudsdk.tencent.sms.response.SendStatus;
import com.mizhousoft.cloudsdk.tencent.sms.response.SmsPackagesStatisticsResponse;
import com.mizhousoft.commons.lang.ListUtils;

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
	 * @param endpoint
	 * @param region
	 * @param credential
	 */
	public DefaultSmsClient(String endpoint, RegionEnum region, Credential credential)
	{
		super(endpoint, API_VERSION, region, credential, new ClientProfile());
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
	public void send(String phoneNumber, Map<String, String> paramMap, String appId, String sign, String templateId)
	        throws CloudSDKNewException
	{
		String[] phoneNumbers = { phoneNumber };

		multiSend(phoneNumbers, paramMap, appId, sign, templateId);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws CloudSDKNewException
	 */
	@Override
	public void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String appId, String sign, String templateId)
	        throws CloudSDKNewException
	{
		List<String> numberList = new ArrayList<String>(Arrays.asList(phoneNumbers));
		List<List<String>> parts = ListUtils.partition(numberList, 200);

		List<String> failedPhoneNumbers = new ArrayList<>(5);
		String message = null;

		for (List<String> part : parts)
		{
			SendSmsRequest request = new SendSmsRequest();

			request.setSmsSdkAppid(appId);
			request.setSign(sign);
			request.setTemplateID(templateId);

			Collection<String> paramValues = paramMap.values();
			String[] templateParamSet = paramValues.toArray(new String[paramValues.size()]);
			request.setTemplateParamSet(templateParamSet);

			String[] numbers = part.toArray(new String[part.size()]);
			request.setPhoneNumberSet(numbers);

			DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
			        .name("SendSms")
			        .protocol(profile.getProtocol())
			        .endpoint(endpoint)
			        .httpMethod(HttpMethod.POST)
			        .contentType(MediaType.APPLICATION_JSON)
			        .bodyAsString(request)
			        .build();

			Map<String, String> headerMap = buildSignHeader(httpRequest, profile, credential);

			SendSmsResponse response = executeRequest(httpRequest, headerMap, new TypeReference<APIResponse<SendSmsResponse>>()
			{
			});

			SendStatus[] statusList = response.getSendStatusSet();
			for (SendStatus status : statusList)
			{
				if (!"ok".equalsIgnoreCase(status.getCode()))
				{
					failedPhoneNumbers.add(status.getPhoneNumber());
					message = status.getMessage();
				}
			}
		}

		if (!failedPhoneNumbers.isEmpty())
		{
			SmsException exception = new SmsException("SMS appId is " + appId + ", template id is " + templateId + ", " + message);
			exception.setFailedPhoneNumbers(failedPhoneNumbers.toArray(new String[failedPhoneNumbers.size()]));

			throw exception;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsPackagesStatisticsResponse querySmsPackageStatistics(SmsPackageStatisticsRequest request) throws CloudSDKNewException
	{
		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("SmsPackagesStatistics")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(request)
		        .build();

		Map<String, String> headerMap = buildSignHeader(httpRequest, profile, credential);

		SmsPackagesStatisticsResponse response = executeRequest(httpRequest, headerMap,
		        new TypeReference<APIResponse<SmsPackagesStatisticsResponse>>()
		        {
		        });

		return response;
	}

}
