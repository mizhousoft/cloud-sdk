package com.mizhousoft.boot.sms.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.service.VerificationCodeSmsService;
import com.mizhousoft.boot.sms.util.SmsLogTracker;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationClient;
import com.mizhousoft.cloudsdk.sms2.SmsException;
import com.mizhousoft.cloudsdk.sms2.SmsTemplate;
import com.mizhousoft.cloudsdk.tencent.sms.SmsClient;

/**
 * 短信应用服务
 *
 * @version
 */
public class SmsApplicationClientImpl implements SmsApplicationClient
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsApplicationClientImpl.class);

	/**
	 * SmsApplication
	 */
	private SmsApplication application;

	/**
	 * SmsClient
	 */
	private SmsClient smsClient;

	/**
	 * 验证码服务
	 */
	private VerificationCodeSmsService verificationCodeSmsService;

	/**
	 * <templateCode, CloudSmsTemplate>
	 */
	private Map<String, SmsTemplate> templateMap = new ConcurrentHashMap<>(10);

	/**
	 * 构造函数
	 *
	 * @param application
	 * @param smsClient
	 */
	public SmsApplicationClientImpl(SmsApplication application, SmsClient smsClient)
	{
		super();
		this.application = application;
		this.smsClient = smsClient;

		this.verificationCodeSmsService = new VerificationCodeSmsServiceImpl(application, smsClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String templateCode) throws SmsException
	{
		if (ArrayUtils.isEmpty(phoneNumbers))
		{
			throw new SmsException("sms.send.phone.number.empty", "Phone number is null.");
		}

		SmsTemplate smsTemplate = getSmsTemplate(templateCode);

		try
		{
			smsClient.multiSend(phoneNumbers, paramMap, application.getAppId(), smsTemplate.getSignName(), smsTemplate.getTemplateCode());

			SmsLogTracker.log(application, phoneNumbers, true, smsTemplate);
		}
		catch (Throwable e)
		{
			SmsLogTracker.log(application, phoneNumbers, false, smsTemplate);

			throw new SmsException("sms.send.failed", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sendNotification(String phoneNumber, Map<String, String> paramMap, String templateCode) throws SmsException
	{
		if (null == phoneNumber)
		{
			throw new SmsException("sms.send.phone.number.empty", "Phone number is null.");
		}

		SmsTemplate smsTemplate = getSmsTemplate(templateCode);

		try
		{
			smsClient.send(phoneNumber, paramMap, application.getAppId(), smsTemplate.getSignName(), smsTemplate.getTemplateCode());

			SmsLogTracker.log(application, phoneNumber, true, smsTemplate);

			return true;
		}
		catch (Throwable e)
		{
			SmsLogTracker.log(application, phoneNumber, false, smsTemplate);

			LOG.error(e.getMessage(), e);

			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String sendVerificationCode(String phoneNumber, String host, String templateCode) throws SmsException
	{
		if (null == phoneNumber)
		{
			throw new SmsException("sms.send.phone.number.empty", "Phone number is null.");
		}

		SmsTemplate smsTemplate = getSmsTemplate(templateCode);

		try
		{
			String code = verificationCodeSmsService.sendVerificationCode(phoneNumber, host, smsTemplate);

			SmsLogTracker.log(application, phoneNumber, true, host, smsTemplate);

			return code;
		}
		catch (Throwable e)
		{
			SmsLogTracker.log(application, phoneNumber, false, host, smsTemplate);

			LOG.error(e.getMessage(), e);

			throw new SmsException("sms.send.failed", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verify(String phoneNumber, String code, String templateCode) throws SmsException
	{
		if (null == phoneNumber)
		{
			throw new SmsException("sms.send.phone.number.empty", "Phone number is null.");
		}
		else if (null == code)
		{
			throw new SmsException("sms.verification.code.empty", "Code is null.");
		}

		SmsTemplate smsTemplate = getSmsTemplate(templateCode);

		verificationCodeSmsService.verify(phoneNumber, code, smsTemplate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsTemplate getSmsTemplate(String templateCode) throws SmsException
	{
		SmsTemplate smsTemplate = templateMap.get(templateCode);
		if (null == smsTemplate)
		{
			throw new SmsException("sms.template.not.found", templateCode + " sms template not found.");
		}

		return smsTemplate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSmsTemplate(SmsTemplate template)
	{
		templateMap.put(template.getTemplateCode(), template);

		LOG.info("Register sms template to container, body is {}.", template.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SmsTemplate removeSmsTemplate(String templateCode)
	{
		SmsTemplate template = templateMap.remove(templateCode);

		LOG.info("Register sms template to container, body is {}.", template);

		return template;
	}
}
