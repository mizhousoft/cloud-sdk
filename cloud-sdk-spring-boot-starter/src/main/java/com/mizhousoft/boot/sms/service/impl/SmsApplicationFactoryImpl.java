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
import com.mizhousoft.cloudsdk.sms2.SmsApplicationClient;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationFactory;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.sms.impl.DefaultSmsClient;

/**
 * 短信应用工厂类
 *
 * @version
 */
@Service
@Order(value = Integer.MIN_VALUE)
public class SmsApplicationFactoryImpl implements SmsApplicationFactory, CommandLineRunner
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsApplicationFactoryImpl.class);

	/**
	 * SmsApplicationProperties
	 */
	@Autowired
	private SmsApplicationProperties applicationProperties;

	/**
	 * <Name, SmsApplicationClient>
	 */
	private Map<String, SmsApplicationClient> clientMap = new ConcurrentHashMap<>(10);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsApplicationClient getByName(String name)
	{
		SmsApplicationClient smsAppClient = clientMap.get(name);

		return smsAppClient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(String... args) throws Exception
	{
		List<SmsApplication> applications = applicationProperties.getApplications();

		Map<String, SmsApplicationClient> clientMap = new ConcurrentHashMap<>(10);

		if (null != applications)
		{
			for (SmsApplication application : applications)
			{
				SmsApplicationClient smsAppClient = buildSmsAppClient(application);
				clientMap.put(application.getName(), smsAppClient);
			}
		}

		this.clientMap = clientMap;

		LOG.info("Init sms application client successfully, vendor are {}.", StringUtils.join(clientMap.keySet().iterator(), ","));
	}

	/**
	 * 构建短信应用客户端
	 * 
	 * @param application
	 * @return
	 * @throws CloudSDKException
	 */
	private SmsApplicationClient buildSmsAppClient(SmsApplication application) throws CloudSDKException
	{
		if (CloudProvider.TENCENT.isSelf(application.getVendor()))
		{
			Credential credential = new Credential();
			credential.setAccessKey(application.getSecretId());
			credential.setSecretKey(application.getSecretKey());

			DefaultSmsClient smsClient = new DefaultSmsClient(application.getEndpoint(), RegionEnum.get(application.getRegion()),
			        credential);

			SmsApplicationClientImpl smsAppClient = new SmsApplicationClientImpl(application, smsClient);

			return smsAppClient;
		}
		else
		{
			throw new CloudSDKException(application.getVendor() + " not support.");
		}
	}
}
