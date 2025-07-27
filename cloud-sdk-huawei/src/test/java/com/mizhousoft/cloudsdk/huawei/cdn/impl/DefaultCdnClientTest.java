package com.mizhousoft.cloudsdk.huawei.cdn.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTaskDetailsRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTaskDetailsResponse;
import com.mizhousoft.cloudsdk.huawei.core.auth.ICredential;
import com.mizhousoft.commons.httpclient.unirest.UnirestLogInterceptor;

import kong.unirest.core.Unirest;

/**
 * DefaultCdnClient Test
 *
 * @version
 */
public class DefaultCdnClientTest
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCdnClientTest.class);

	private static final String ACCESS_KEY = "";

	private static final String SECRET_KEY = "";

	@Test
	public void showHistoryTaskDetails()
	{
		Unirest.config().interceptor(new UnirestLogInterceptor());

		try
		{
			ICredential credential = new ICredential();
			credential.setAccessKey(ACCESS_KEY);
			credential.setSecretKey(SECRET_KEY);

			ShowHistoryTaskDetailsRequest request = new ShowHistoryTaskDetailsRequest();
			request.setHistoryTasksId("3806988254");
			request.setPageSize(10);
			request.setPageNumber(1);

			DefaultCdnClient cdnClient = new DefaultCdnClient(credential);

			ShowHistoryTaskDetailsResponse response = cdnClient.showHistoryTaskDetails(request);

			LOG.info("Result: {}", response);

			Assertions.assertNotNull(response);
		}
		catch (Exception e)
		{
			Assertions.fail(e.getMessage(), e);
		}
	}
}
