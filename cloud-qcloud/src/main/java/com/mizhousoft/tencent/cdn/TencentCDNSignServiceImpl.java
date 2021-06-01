package com.mizhousoft.tencent.cdn;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;

/**
 * CDN签名服务
 *
 * @version
 */
public class TencentCDNSignServiceImpl implements CDNSignService
{
	private static final Logger LOG = LoggerFactory.getLogger(TencentCDNSignServiceImpl.class);

	private CDNProfile profile;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String signUrl(String objectName, long signExpiredMs)
	{
		if (!StringUtils.startsWith(objectName, "/"))
		{
			objectName = '/' + objectName;
		}

		String url = null;
		if (profile.isUrlAuthzEnable())
		{
			String expireTime = (System.currentTimeMillis() + signExpiredMs) / 1000 + "";
			String data = profile.getSecretKey() + objectName + expireTime;
			String sign = DigestUtils.md5Hex(data);
			url = profile.getEndpoint() + objectName + "?sign=" + sign + "&t=" + expireTime;
		}
		else
		{
			url = profile.getEndpoint() + objectName;
		}

		return url;
	}

	public void init(CDNProfile profile)
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
}
