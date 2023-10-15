package com.mizhousoft.boot.sms.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SmsTemplateContainer;

/**
 * 短信模板容器
 *
 * @version
 */
@Service
public class SmsTemplateContainerImpl implements SmsTemplateContainer
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsTemplateContainerImpl.class);

	// <templateCode, CloudSmsTemplate>
	private Map<String, CloudSmsTemplate> templateMap = new ConcurrentHashMap<>(10);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CloudSmsTemplate getByTemplateCode(String templateCode)
	{
		return templateMap.get(templateCode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register(CloudSmsTemplate template)
	{
		templateMap.put(template.getTemplateCode(), template);

		LOG.info("Register sms template to container, body is {}.", template.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CloudSmsTemplate deregister(String templateCode)
	{
		CloudSmsTemplate template = templateMap.remove(templateCode);

		LOG.info("Register sms template to container, body is {}.", template);

		return template;
	}
}
