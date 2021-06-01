package com.mizhousoft.aliyun.cdn;

import org.junit.Before;
import org.junit.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;

/**
 * AliyunCDNSignServiceImpl Test
 *
 * @version
 */
public class TestAliyunCDNSignServiceImpl
{
	private static final String ENDPOINT = "";

	private static final String OBJECTNAME = "test/test.txt";

	private CDNSignService cdnSignService;

	@Before
	public void before() throws CloudSDKException
	{
		CDNProfile profile = new CDNProfile();
		profile.setEndpoint(ENDPOINT);

		AliyunCDNSignServiceImpl service = new AliyunCDNSignServiceImpl();
		service.init(profile);

		this.cdnSignService = service;
	}

	@Test
	public void testsignUrl()
	{
		long signExpiredMs = 60 * 1000;

		String url = cdnSignService.signUrl(OBJECTNAME, signExpiredMs);
		System.out.println(url);
	}
}
