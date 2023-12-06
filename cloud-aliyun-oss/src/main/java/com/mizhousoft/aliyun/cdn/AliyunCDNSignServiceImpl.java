package com.mizhousoft.aliyun.cdn;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
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
		if (StringUtils.endsWith(endpoint, "/"))
		{
			endpoint = endpoint.substring(0, endpoint.length() - 1);
			profile.setEndpoint(endpoint);

			LOG.info("Remove cdn endpoint {} last char /.", endpoint);
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			profile.setUrlAuthzEnable(false);
		}

		this.profile = profile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String signUrl(String path, long signExpiredMs)
	{
		if (!StringUtils.startsWith(path, "/"))
		{
			path = '/' + path;
		}

		String url = null;
		if (profile.isUrlAuthzEnable())
		{
			String expireTime = (System.currentTimeMillis() + signExpiredMs) / 1000 + "";
			String rand = RandomGenerator.genHexString(5, true);
			String data = path + "-" + expireTime + "-" + rand + "-0-" + profile.getSecretKey();
			String sign = DigestUtils.md5Hex(data);

			String authKey = expireTime + "-" + rand + "-0-" + sign;
			url = profile.getEndpoint() + path + "?auth_key=" + authKey;
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
