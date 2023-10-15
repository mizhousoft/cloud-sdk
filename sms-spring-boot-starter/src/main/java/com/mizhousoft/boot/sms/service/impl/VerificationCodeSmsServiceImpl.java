package com.mizhousoft.boot.sms.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mizhousoft.boot.sms.model.SmsVerificationCode;
import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.service.SmsWindControlService;
import com.mizhousoft.boot.sms.service.VerificationCodeSmsService;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SendSmsClient;
import com.mizhousoft.cloudsdk.sms.SmsSendException;

/**
 * 验证码短信服务
 *
 * @version
 */
public class VerificationCodeSmsServiceImpl implements VerificationCodeSmsService
{
	private SmsApplication application;

	private SendSmsClient sendSmsClient;

	private SmsWindControlService smsWindControlService;

	// <phoneNumber-templateCode, SmsVerificationCode>
	private Cache<String, SmsVerificationCode> cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();

	/**
	 * 构造函数
	 *
	 * @param application
	 * @param sendSmsClient
	 */
	public VerificationCodeSmsServiceImpl(SmsApplication application, SendSmsClient sendSmsClient)
	{
		super();
		this.application = application;
		this.sendSmsClient = sendSmsClient;
		this.smsWindControlService = new SmsWindControlServiceImpl(application);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String sendVerificationCode(String phoneNumber, String host, CloudSmsTemplate smsTemplate) throws SmsSendException
	{
		boolean ok = smsWindControlService.determineRequestOk(phoneNumber, host);
		if (!ok)
		{
			throw new SmsSendException(phoneNumber + " phone number is illegal, host is " + host);
		}

		int validTime = 10;

		String verificationCode = null;

		String cacheKey = getCacheKey(phoneNumber, smsTemplate);

		// 每次先判断手机是否发送过验证码，发送过就使用第一次发送的验证码
		SmsVerificationCode smsVerificationCode = getSmsVerificationCode(cacheKey);
		if (null != smsVerificationCode)
		{
			verificationCode = smsVerificationCode.getVerificationCode();
		}
		else
		{
			verificationCode = genRandomCode();
		}

		// 短信模板配置固化
		Map<String, String> paramMap = new LinkedHashMap<>(2);
		paramMap.put("verificationCode", verificationCode);
		paramMap.put("validTime", String.valueOf(validTime));

		sendSmsClient.send(phoneNumber, paramMap, application.getAppId(), smsTemplate);

		smsVerificationCode = new SmsVerificationCode(host, verificationCode);
		cache.put(cacheKey, smsVerificationCode);

		return verificationCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verify(String phoneNumber, String code, CloudSmsTemplate smsTemplate) throws SmsSendException
	{
		String cacheKey = getCacheKey(phoneNumber, smsTemplate);

		SmsVerificationCode smsVerificationCode = getSmsVerificationCode(cacheKey);
		if (null == smsVerificationCode)
		{
			throw new SmsSendException("sms.verification.code.verify.null", "Verification code is null.");
		}
		else if (!smsVerificationCode.getVerificationCode().equals(code))
		{
			throw new SmsSendException("sms.verification.code.verify.wrong", "Verification code is wrong.");
		}
		else
		{
			invalidateCode(cacheKey);
			smsWindControlService.invalidate(phoneNumber, smsVerificationCode.getHost());
		}
	}

	private String getCacheKey(String phoneNumber, CloudSmsTemplate smsTemplate)
	{
		return phoneNumber + "-" + smsTemplate.getTemplateCode();
	}

	private SmsVerificationCode getSmsVerificationCode(String cacheKey)
	{
		SmsVerificationCode result = cache.getIfPresent(cacheKey);
		return result;
	}

	private void invalidateCode(String cacheKey)
	{
		if (null != cacheKey)
		{
			cache.invalidate(cacheKey);
		}
	}

	private String genRandomCode()
	{
		// SecureRandom random = new SecureRandom();
		// StringBuffer buffer = new StringBuffer(6);
		// random.ints(6, 0, 9).forEach(value -> buffer.append(value));
		//
		// String verifyCode = buffer.toString();

		String verifyCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
		return verifyCode;
	}
}
