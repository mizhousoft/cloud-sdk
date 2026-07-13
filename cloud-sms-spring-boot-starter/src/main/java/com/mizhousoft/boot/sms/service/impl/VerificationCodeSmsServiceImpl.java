package com.mizhousoft.boot.sms.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mizhousoft.boot.sms.model.SmsVerificationCode;
import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.service.VerificationCodeSmsService;
import com.mizhousoft.boot.sms.service.WindControlService;
import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.sms2.SmsException;
import com.mizhousoft.cloudsdk.sms2.SmsTemplate;
import com.mizhousoft.cloudsdk.tencent.sms.SmsClient;

/**
 * 验证码短信服务
 *
 * @version
 */
public class VerificationCodeSmsServiceImpl implements VerificationCodeSmsService
{
	/**
	 * 短信应用
	 */
	private SmsApplication application;

	/**
	 * 短信客户端
	 */
	private SmsClient smsClient;

	/**
	 * 风控服务
	 */
	private WindControlService windControlService;

	/**
	 * <phoneNumber-templateCode, SmsVerificationCode>
	 */
	private Cache<String, SmsVerificationCode> cache = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build();

	/**
	 * 构造函数
	 *
	 * @param application
	 * @param smsClient
	 */
	public VerificationCodeSmsServiceImpl(SmsApplication application, SmsClient smsClient)
	{
		super();
		this.application = application;
		this.smsClient = smsClient;
		this.windControlService = new WindControlServiceImpl(application);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws SmsException
	 */
	@Override
	public String sendVerificationCode(String phoneNumber, String host, SmsTemplate smsTemplate) throws SmsException
	{
		boolean ok = windControlService.canSendSms(phoneNumber, host);
		if (!ok)
		{
			throw new SmsException("sms.send.failed", phoneNumber + " phone number is illegal, host is " + host);
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

		try
		{
			smsClient.send(phoneNumber, paramMap, application.getAppId(), smsTemplate);
		}
		catch (CloudSDKNewException e)
		{
			SmsException cause = new SmsException("sms.send.failed", "Sms send failed.", e);
			cause.setHttpStatusCode(e.getHttpStatusCode());
			cause.setRequestId(e.getRequestId());
			cause.setRequestErrorCode(e.getRequestErrorCode());

			throw cause;
		}

		smsVerificationCode = new SmsVerificationCode(host, verificationCode);
		cache.put(cacheKey, smsVerificationCode);

		return verificationCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verify(String phoneNumber, String code, SmsTemplate smsTemplate) throws SmsException
	{
		String cacheKey = getCacheKey(phoneNumber, smsTemplate);

		SmsVerificationCode smsVerificationCode = getSmsVerificationCode(cacheKey);
		if (null == smsVerificationCode)
		{
			throw new SmsException("sms.verification.code.verify.null", "Verification code is null.");
		}
		else if (!smsVerificationCode.getVerificationCode().equals(code))
		{
			throw new SmsException("sms.verification.code.verify.wrong", "Verification code is wrong.");
		}
		else
		{
			invalidateCode(cacheKey);
			windControlService.invalidate(phoneNumber, smsVerificationCode.getHost());
		}
	}

	/**
	 * 获取缓存key
	 * 
	 * @param phoneNumber
	 * @param smsTemplate
	 * @return
	 */
	private String getCacheKey(String phoneNumber, SmsTemplate smsTemplate)
	{
		return phoneNumber + "-" + smsTemplate.getTemplateCode();
	}

	/**
	 * 获取验证码
	 * 
	 * @param cacheKey
	 * @return
	 */
	private SmsVerificationCode getSmsVerificationCode(String cacheKey)
	{
		SmsVerificationCode result = cache.getIfPresent(cacheKey);
		return result;
	}

	/**
	 * 让验证码失效
	 * 
	 * @param cacheKey
	 */
	private void invalidateCode(String cacheKey)
	{
		if (null != cacheKey)
		{
			cache.invalidate(cacheKey);
		}
	}

	/**
	 * 生成验证码
	 * 
	 * @return
	 */
	private String genRandomCode()
	{
		String verifyCode = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

		return verifyCode;
	}
}
