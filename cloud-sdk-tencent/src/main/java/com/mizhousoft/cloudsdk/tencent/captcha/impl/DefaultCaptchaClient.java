package com.mizhousoft.cloudsdk.tencent.captcha.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.captcha.CaptchaRequest;
import com.mizhousoft.cloudsdk.tencent.captcha.CaptchaClient;
import com.mizhousoft.cloudsdk.tencent.captcha.request.DescribeCaptchaResultRequest;
import com.mizhousoft.cloudsdk.tencent.captcha.response.DescribeCaptchaResultResponse;
import com.mizhousoft.cloudsdk.tencent.common.APIResponse;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;

import kong.unirest.core.HttpMethod;
import tools.jackson.core.type.TypeReference;

/**
 * 验证码客户端
 *
 * @version
 */
public class DefaultCaptchaClient extends AbstractClient implements CaptchaClient
{
	private static final String ENDPOINT = "captcha.tencentcloudapi.com";

	private static final String API_VERSION = "2019-07-22";

	/**
	 * 验证码应用ID
	 */
	private final Long captchaAppId;

	/**
	 * 验证码应用密钥
	 */
	private final String captchaAppSecret;

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 * @param captchaAppId
	 * @param captchaAppSecret
	 */
	public DefaultCaptchaClient(RegionEnum region, Credential credential, Long captchaAppId, String captchaAppSecret)
	{
		this(ENDPOINT, API_VERSION, region, credential, new ClientProfile(), captchaAppId, captchaAppSecret);
	}

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 * @param profile
	 * @param captchaAppId
	 * @param captchaAppSecret
	 */
	public DefaultCaptchaClient(RegionEnum region, Credential credential, ClientProfile profile, Long captchaAppId, String captchaAppSecret)
	{
		this(ENDPOINT, API_VERSION, region, credential, profile, captchaAppId, captchaAppSecret);
	}

	/**
	 * 构造函数
	 *
	 * @param endpoint
	 * @param apiVersion
	 * @param region
	 * @param credential
	 * @param profile
	 * @param captchaAppId
	 * @param captchaAppSecret
	 */
	public DefaultCaptchaClient(String endpoint, String apiVersion, RegionEnum region, Credential credential, ClientProfile profile,
	        Long captchaAppId, String captchaAppSecret)
	{
		super(endpoint, apiVersion, region, credential, profile);
		this.captchaAppId = captchaAppId;
		this.captchaAppSecret = captchaAppSecret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DescribeCaptchaResultResponse verify(CaptchaRequest request) throws CloudSDKNewException
	{
		DescribeCaptchaResultRequest apiRequest = new DescribeCaptchaResultRequest();
		apiRequest.setCaptchaType(9);
		apiRequest.setTicket(request.getTicket());
		apiRequest.setUserIp(request.getUserIp());
		apiRequest.setRandstr(request.getRandstr());
		apiRequest.setCaptchaAppId(captchaAppId);
		apiRequest.setAppSecretKey(captchaAppSecret);
		apiRequest.setBusinessId(request.getBusinessId());
		apiRequest.setSceneId(request.getSceneId());
		if (request.isNeedGetCaptchaTime())
		{
			apiRequest.setNeedGetCaptchaTime(1);
		}

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("DescribeCaptchaResult")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(apiRequest)
		        .build();

		Map<String, String> headerMap = buildSignHeader(httpRequest, profile, credential);

		DescribeCaptchaResultResponse response = executeRequest(httpRequest,
		        headerMap,
		        new TypeReference<APIResponse<DescribeCaptchaResultResponse>>()
		        {
		        });

		int captchaCode = response.getCaptchaCode();
		if (1 != captchaCode)
		{
			throw new CloudSDKNewException(response.getCaptchaMsg());
		}

		return response;
	}
}
