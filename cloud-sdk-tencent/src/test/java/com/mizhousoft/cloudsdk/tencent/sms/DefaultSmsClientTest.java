package com.mizhousoft.cloudsdk.tencent.sms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.auth.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.auth.Credential;
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

		ClientProfile profile = new ClientProfile();
		profile.setRegion("ap-guangzhou");

		Credential credential = new Credential();
		credential.setAccessKey(ACCESS_KEY);
		credential.setSecretKey(SECRET_KEY);

		smsClient = new DefaultSmsClient("ap-guangzhou", credential, profile);
	}

	@Test
	public void querySmsPackageStatistics() throws CloudSDKException
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
}
