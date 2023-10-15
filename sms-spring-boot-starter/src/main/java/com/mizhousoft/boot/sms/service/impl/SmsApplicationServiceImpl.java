package com.mizhousoft.boot.sms.service.impl;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.service.VerificationCodeSmsService;
import com.mizhousoft.boot.sms.util.SmsBILogger;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SendSmsClient;
import com.mizhousoft.cloudsdk.sms.SmsApplicationService;
import com.mizhousoft.cloudsdk.sms.SmsSendException;
import com.mizhousoft.cloudsdk.sms.SmsTemplateContainer;

/**
 * 短信应用服务
 *
 * @version
 */
public class SmsApplicationServiceImpl implements SmsApplicationService
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsApplicationServiceImpl.class);

	private SmsApplication application;

	private SendSmsClient sendSmsClient;

	private SmsTemplateContainer smsTemplateContainer;

	private VerificationCodeSmsService verificationCodeSmsService;

	/**
	 * 构造函数
	 *
	 * @param application
	 * @param sendSmsClient
	 * @param smsTemplateContainer
	 */
	public SmsApplicationServiceImpl(SmsApplication application, SendSmsClient sendSmsClient, SmsTemplateContainer smsTemplateContainer)
	{
		super();
		this.application = application;
		this.sendSmsClient = sendSmsClient;
		this.smsTemplateContainer = smsTemplateContainer;

		this.verificationCodeSmsService = new VerificationCodeSmsServiceImpl(application, sendSmsClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String templateCode) throws SmsSendException
	{
		if (ArrayUtils.isEmpty(phoneNumbers))
		{
			throw new SmsSendException("sms.send.phone.number.empty", "Phone number is null.");
		}

		CloudSmsTemplate smsTemplate = getBySmsTemplate(templateCode);

		try
		{
			sendSmsClient.multiSend(phoneNumbers, paramMap, application.getAppId(), smsTemplate);

			SmsBILogger.log(phoneNumbers, true, smsTemplate);
		}
		catch (Throwable e)
		{
			SmsBILogger.log(phoneNumbers, false, smsTemplate);

			throw new SmsSendException("sms.send.failed", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean sendNotification(String phoneNumber, Map<String, String> paramMap, String templateCode) throws SmsSendException
	{
		if (null == phoneNumber)
		{
			throw new SmsSendException("sms.send.phone.number.empty", "Phone number is null.");
		}

		CloudSmsTemplate smsTemplate = getBySmsTemplate(templateCode);

		try
		{
			sendSmsClient.send(phoneNumber, paramMap, application.getAppId(), smsTemplate);

			SmsBILogger.log(phoneNumber, true, smsTemplate);

			return true;
		}
		catch (Throwable e)
		{
			SmsBILogger.log(phoneNumber, false, smsTemplate);

			LOG.error(e.getMessage(), e);

			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String sendVerificationCode(String phoneNumber, String host, String templateCode) throws SmsSendException
	{
		if (null == phoneNumber)
		{
			throw new SmsSendException("sms.send.phone.number.empty", "Phone number is null.");
		}

		CloudSmsTemplate smsTemplate = getBySmsTemplate(templateCode);

		try
		{
			String code = verificationCodeSmsService.sendVerificationCode(phoneNumber, host, smsTemplate);

			SmsBILogger.log(phoneNumber, true, host, smsTemplate);

			return code;
		}
		catch (Throwable e)
		{
			SmsBILogger.log(phoneNumber, false, host, smsTemplate);

			LOG.error(e.getMessage(), e);

			throw new SmsSendException("sms.send.failed", e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verify(String phoneNumber, String code, String templateCode) throws SmsSendException
	{
		if (null == phoneNumber)
		{
			throw new SmsSendException("sms.send.phone.number.empty", "Phone number is null.");
		}
		else if (null == code)
		{
			throw new SmsSendException("sms.verification.code.empty", "Code is null.");
		}

		CloudSmsTemplate smsTemplate = getBySmsTemplate(templateCode);

		verificationCodeSmsService.verify(phoneNumber, code, smsTemplate);
	}

	private CloudSmsTemplate getBySmsTemplate(String templateCode) throws SmsSendException
	{
		CloudSmsTemplate smsTemplate = smsTemplateContainer.getByTemplateCode(templateCode);
		if (null == smsTemplate)
		{
			throw new SmsSendException("sms.template.not.found", templateCode + " sms template not found.");
		}

		return smsTemplate;
	}
}
