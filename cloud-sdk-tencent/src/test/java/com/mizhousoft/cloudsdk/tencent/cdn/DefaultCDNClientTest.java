package com.mizhousoft.cloudsdk.tencent.cdn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.tencent.cdn.impl.DefaultCDNClient;

/**
 * DefaultCDNClient Test
 *
 * @version
 */
public class DefaultCDNClientTest
{
	private static final String ENDPOINT = "";

	private static final String OBJECTNAME = "test/test.txt";

	private DefaultCDNClient cdnClient;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		CDNProfile profile = new CDNProfile();
		profile.setEndpoint(ENDPOINT);

		DefaultCDNClient cdnClient = new DefaultCDNClient(profile);
		this.cdnClient = cdnClient;
	}

	@Test
	public void signUrl()
	{
		long signExpiredMs = 60 * 1000;

		String url = cdnClient.signUrl(OBJECTNAME, 0, signExpiredMs);
		System.out.println(url);
	}
}
