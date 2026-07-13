package com.mizhousoft.cloudsdk.tencent.captcha;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.captcha.CaptchaRequest;
import com.mizhousoft.cloudsdk.tencent.captcha.impl.DefaultCaptchaClient;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.commons.httpclient.unirest.UnirestLogInterceptor;

import kong.unirest.core.Unirest;

/**
 * DefaultCaptchaClient Test
 *
 */
public class DefaultCaptchaClientTest
{
	private static DefaultCaptchaClient captchaClient;

	@BeforeAll
	public static void before()
	{
		Unirest.config().interceptor(new UnirestLogInterceptor());

		Credential credential = new Credential();
		credential.setAccessKey("");
		credential.setSecretKey("");

		Long captchaAppId = 0L;
		String captchaAppSecret = "";

		captchaClient = new DefaultCaptchaClient(RegionEnum.GUANGZHOU, credential, captchaAppId, captchaAppSecret);
	}

	@Test
	public void verify() throws CloudSDKNewException
	{
		CaptchaRequest request = new CaptchaRequest();
		request.setRandstr("");
		request.setTicket("");
		request.setUserIp("");

		captchaClient.verify(request);
	}

}
