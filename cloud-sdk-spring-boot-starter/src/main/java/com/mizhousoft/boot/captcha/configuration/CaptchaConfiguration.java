package com.mizhousoft.boot.captcha.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mizhousoft.boot.captcha.properties.CaptchaProperties;
import com.mizhousoft.cloudsdk.CloudProvider;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.captcha.CaptchaClient;
import com.mizhousoft.cloudsdk.tencent.captcha.impl.DefaultCaptchaClient;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;

/**
 * 验证码配置
 *
 * @version
 */
@Configuration
@ConditionalOnProperty(name = "cloud.captcha.access-key", matchIfMissing = false)
public class CaptchaConfiguration
{
	@Autowired
	private CaptchaProperties captchaProperties;

	@Bean
	public CaptchaClient getCaptchaClient() throws CloudSDKException
	{
		if (CloudProvider.TENCENT.isSelf(captchaProperties.getVendor()))
		{
			Credential credential = new Credential();
			credential.setAccessKey(captchaProperties.getAccessKey());
			credential.setSecretKey(captchaProperties.getSecretKey());

			RegionEnum regionEnum = RegionEnum.get(captchaProperties.getRegion());

			DefaultCaptchaClient captchaClient = new DefaultCaptchaClient(
			        regionEnum,
			        credential,
			        captchaProperties.getCaptchaAppId(),
			        captchaProperties.getCaptchaAppSecret());

			return captchaClient;
		}
		else
		{
			throw new CloudSDKException(captchaProperties.getVendor() + " not support.");
		}
	}
}
