package com.mizhousoft.cloudsdk.tencent.cdn.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.tencent.cdn.CDNClient;
import com.mizhousoft.commons.crypto.generator.RandomGenerator;

/**
 * CDNClient
 *
 * @version
 */
public class DefaultCDNClient implements CDNClient
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCDNClient.class);

	private static final String TYPE_A = "TypeA";

	private static final String TYPE_D = "TypeD";

	/**
	 * CDNProfile
	 */
	private volatile CDNProfile profile;

	/**
	 * 构造函数
	 *
	 * @param profile
	 */
	public DefaultCDNClient(CDNProfile profile)
	{
		String endpoint = profile.getEndpoint();
		if (Strings.CS.endsWith(endpoint, "/"))
		{
			endpoint = endpoint.substring(0, endpoint.length() - 1);
			profile.setEndpoint(endpoint);

			LOG.info("Remove cdn endpoint {} last char /.", endpoint);
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			profile.setAuthzEnable(false);
			profile.setAuthzMode(null);
		}

		this.profile = profile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String signUrl(String objectName, long uid, long signExpiredMs)
	{
		if (!Strings.CS.startsWith(objectName, "/"))
		{
			objectName = '/' + objectName;
		}

		String url = null;
		if (profile.isAuthzEnable())
		{
			if (TYPE_A.equals(profile.getAuthzMode()))
			{
				String expireTime = (System.currentTimeMillis() + signExpiredMs) / 1000 + "";
				String rand = RandomGenerator.genHexString(5, true);
				String data = objectName + "-" + expireTime + "-" + rand + "-" + uid + "-" + profile.getSecretKey();
				String sign = DigestUtils.md5Hex(data);

				String authKey = expireTime + "-" + rand + "-" + uid + "-" + sign;
				url = profile.getEndpoint() + objectName + "?sign=" + authKey;
			}
			else if (TYPE_D.equals(profile.getAuthzMode()))
			{
				String expireTime = (System.currentTimeMillis() + signExpiredMs) / 1000 + "";
				String data = profile.getSecretKey() + objectName + expireTime;
				String sign = DigestUtils.md5Hex(data);
				url = profile.getEndpoint() + objectName + "?sign=" + sign + "&t=" + expireTime;
			}
		}
		else
		{
			url = profile.getEndpoint() + objectName;
		}

		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint()
	{
		return profile.getEndpoint();
	}
}
