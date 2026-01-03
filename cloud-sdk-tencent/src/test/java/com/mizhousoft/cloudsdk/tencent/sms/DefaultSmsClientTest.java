package com.mizhousoft.cloudsdk.tencent.sms;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.sms.impl.DefaultSmsClient;
import com.mizhousoft.cloudsdk.tencent.sms.request.SmsPackageStatisticsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.response.SmsPackagesStatisticsResponse;
import com.mizhousoft.commons.httpclient.unirest.UnirestLogInterceptor;

import kong.unirest.core.Unirest;

/**
 * DefaultSmsClient测试用例
 *
 * @version
 */
public class DefaultSmsClientTest
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultSmsClientTest.class);

	private static final String ACCESS_KEY = System.getenv("TENCENTCLOUD_SECRET_ID");

	private static final String SECRET_KEY = System.getenv("TENCENTCLOUD_SECRET_KEY");

	private static DefaultSmsClient smsClient;

	@BeforeAll
	public static void before()
	{
		Unirest.config().interceptor(new UnirestLogInterceptor());

		Credential credential = new Credential();
		credential.setAccessKey(ACCESS_KEY);
		credential.setSecretKey(SECRET_KEY);

		smsClient = new DefaultSmsClient(RegionEnum.GUANGZHOU, credential);
	}

	@Test
	public void send()
	{
		String phoneNumber = "";
		String appId = "";
		String sign = "";
		String templateId = "";
		Map<String, String> paramMap = Map.of("1", "223332", "2", "10");

		try
		{
			smsClient.send(phoneNumber, paramMap, appId, sign, templateId);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e);
		}
	}

	@Test
	public void querySmsPackageStatistics()
	{
		try
		{
			SmsPackageStatisticsRequest request = new SmsPackageStatisticsRequest();
			request.setSmsSdkAppId("1400119865");
			request.setBeginTime("2025050113");
			request.setEndTime("2025120113");
			request.setLimit(1);
			request.setOffset(0);
			SmsPackagesStatisticsResponse response = smsClient.querySmsPackageStatistics(request);

			LOG.info(response.toString());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e);
		}
	}
}
