package com.mizhousoft.cloudsdk.tencent.tms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.ocr.DefaultOcrClientTest;
import com.mizhousoft.cloudsdk.tencent.tms.impl.DefaultTmsClient;
import com.mizhousoft.cloudsdk.tms.TextModerationResult;
import com.mizhousoft.commons.httpclient.unirest.UnirestLogInterceptor;
import com.mizhousoft.commons.json.JSONUtils;

import kong.unirest.core.Unirest;

/**
 * DefaultTmsClient Test
 *
 * @version
 */
public class DefaultTmsClientTest
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultOcrClientTest.class);

	private static final String ACCESS_KEY = System.getenv("TENCENTCLOUD_SECRET_ID");

	private static final String SECRET_KEY = System.getenv("TENCENTCLOUD_SECRET_KEY");

	private static DefaultTmsClient tmsClient;

	@BeforeAll
	public static void before()
	{
		Unirest.config().interceptor(new UnirestLogInterceptor());

		Credential credential = new Credential();
		credential.setAccessKey(ACCESS_KEY);
		credential.setSecretKey(SECRET_KEY);

		tmsClient = new DefaultTmsClient(RegionEnum.GUANGZHOU, credential);
	}

	@Test
	public void genTempCredential() throws CloudSDKNewException
	{
		String text = "零九点三苍井空零附近你吗的";

		TextModerationResult result = tmsClient.execute(text);

		LOG.info(JSONUtils.toJSONStringQuietly(result));
	}

}
