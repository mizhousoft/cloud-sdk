package com.mizhousoft.boot.sms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.mizhousoft.boot.sms.properties.SmsApplicationProperties;
import com.mizhousoft.boot.sms.service.impl.SmsApplicationFactoryImpl;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationFactory;

/**
 * SmsConfiguration
 *
 * @version
 */
public class SmsConfiguration
{
	/**
	 * SmsApplicationProperties
	 */
	@Autowired
	private SmsApplicationProperties applicationProperties;

	@Bean
	@ConditionalOnProperty(name = "cloud.sms.applications[0].name", matchIfMissing = false)
	public SmsApplicationFactory getSmsApplicationFactoryBean() throws CloudSDKException
	{
		SmsApplicationFactoryImpl factory = new SmsApplicationFactoryImpl(applicationProperties);
		factory.createSmsApplication();

		return factory;
	}
}
