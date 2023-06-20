package com.mizhousoft.tencent.boot.tms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tms.TextModerationService;
import com.mizhousoft.tencent.boot.tms.properties.TMSProfileProperties;
import com.mizhousoft.tencent.tms.impl.TextModerationServiceImpl;
import com.mizhousoft.tencent.tms.profile.TMSProfile;

/**
 * TencentTMSConfiguration
 *
 * @version
 */
@Configuration
public class TencentTMSConfiguration
{
	@Autowired
	private TMSProfileProperties properties;

	@Bean
	public TextModerationService getTextModerationService() throws CloudSDKException
	{
		TMSProfile profile = new TMSProfile();
		profile.setAccessKey(properties.getAccessKey());
		profile.setSecretKey(properties.getSecretKey());
		profile.setRegion(properties.getRegion());
		profile.setEndpoint(properties.getEndpoint());

		TextModerationServiceImpl textModerationService = new TextModerationServiceImpl();
		textModerationService.init(profile);

		return textModerationService;
	}
}
