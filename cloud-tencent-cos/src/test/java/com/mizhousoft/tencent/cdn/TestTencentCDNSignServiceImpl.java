package com.mizhousoft.tencent.cdn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;

/**
 * CNDSignServiceImpl测试类
 *
 * @version
 */
public class TestTencentCDNSignServiceImpl
{
	private static final String ENDPOINT = "";

	private static final String OBJECTNAME = "test/test.txt";

	private CDNSignService cdnSignService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		CDNProfile profile = new CDNProfile();
		profile.setEndpoint(ENDPOINT);

		TencentCDNSignServiceImpl service = new TencentCDNSignServiceImpl(profile);
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
