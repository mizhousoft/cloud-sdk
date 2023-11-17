package com.mizhousoft.boot.sms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.properties.SmsApplicationProperties;
import com.mizhousoft.cloudsdk.CloudProvider;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms.SmsApplicationFactory;
import com.mizhousoft.cloudsdk.sms.SmsApplicationService;
import com.mizhousoft.cloudsdk.sms.SmsProfile;
import com.mizhousoft.cloudsdk.sms.SmsTemplateContainer;
import com.mizhousoft.tencent.sms.TencentSendSmsClient;

/**
 * 短信应用工程
 *
 * @version
 */
@Service
@Order(value = Integer.MIN_VALUE)
public class SmsApplicationFactoryImpl implements SmsApplicationFactory, CommandLineRunner
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsApplicationFactoryImpl.class);

	@Autowired
	private SmsApplicationProperties applicationProperties;

	@Autowired
	private SmsTemplateContainer smsTemplateContainer;

	// <Name, SmsApplicationService>
	private Map<String, SmsApplicationService> serviceMap = new ConcurrentHashMap<>(10);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsApplicationService getByName(String name)
	{
		SmsApplicationService service = serviceMap.get(name);

		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(String... args) throws Exception
	{
		List<SmsApplication> applications = applicationProperties.getApplications();

		Map<String, SmsApplicationService> serviceMap = new ConcurrentHashMap<>(10);

		if (null != applications)
		{
			for (SmsApplication application : applications)
			{
				SmsApplicationService service = buildService(application);
				serviceMap.put(application.getName(), service);
			}
		}

		this.serviceMap = serviceMap;

		LOG.info("Init sms application successfully, vendor are {}.", StringUtils.join(serviceMap.keySet().iterator(), ","));
	}

	private SmsApplicationService buildService(SmsApplication application) throws CloudSDKException
	{
		if (CloudProvider.TENCENT.isSelf(application.getVendor()))
		{
			TencentSendSmsClient sendSmsClient = new TencentSendSmsClient();

			SmsProfile profile = new SmsProfile();
			profile.setAccessKeyId(application.getSecretId());
			profile.setAccessKeySecret(application.getSecretKey());
			profile.setEndpoint(application.getEndpoint());
			profile.setRegion(application.getRegion());
			sendSmsClient.init(profile);

			SmsApplicationServiceImpl service = new SmsApplicationServiceImpl(application, sendSmsClient, smsTemplateContainer);

			return service;
		}
		else
		{
			throw new CloudSDKException(application.getVendor() + " not support.");
		}
	}
}
