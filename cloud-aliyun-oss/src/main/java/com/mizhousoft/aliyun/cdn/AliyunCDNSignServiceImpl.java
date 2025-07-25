package com.mizhousoft.aliyun.cdn;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;
import com.mizhousoft.commons.crypto.generator.RandomGenerator;

/**
 * 阿里云CDN签名服务
 *
 * @version
 */
public class AliyunCDNSignServiceImpl implements CDNSignService
{
	private static final Logger LOG = LoggerFactory.getLogger(AliyunCDNSignServiceImpl.class);

	private static final String TYPE_A = "TypeA";

	// Profile
	private CDNProfile profile;

	/**
	 * 构造函数
	 *
	 * @param profile
	 */
	public AliyunCDNSignServiceImpl(CDNProfile profile)
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
	public String signUrl(String path, long uid, long signExpiredMs)
	{
		if (!Strings.CS.startsWith(path, "/"))
		{
			path = '/' + path;
		}

		String url = null;
		if (profile.isAuthzEnable())
		{
			if (TYPE_A.equals(profile.getAuthzMode()))
			{
				String expireTime = (System.currentTimeMillis() + signExpiredMs) / 1000 + "";
				String rand = RandomGenerator.genHexString(5, true);
				String data = path + "-" + expireTime + "-" + rand + "-" + uid + "-" + profile.getSecretKey();
				String sign = DigestUtils.md5Hex(data);

				String authKey = expireTime + "-" + rand + "-" + uid + "-" + sign;
				url = profile.getEndpoint() + path + "?auth_key=" + authKey;
			}
		}
		else
		{
			url = profile.getEndpoint() + path;
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
