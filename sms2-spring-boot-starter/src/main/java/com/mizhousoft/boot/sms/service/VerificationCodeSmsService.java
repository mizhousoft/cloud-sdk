package com.mizhousoft.boot.sms.service;

import com.mizhousoft.cloudsdk.sms2.SmsTemplate;
import com.mizhousoft.cloudsdk.sms2.SmsException;

/**
 * 验证码短信服务
 *
 * @version
 */
public interface VerificationCodeSmsService
{
	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param host
	 * @param smsTemplate
	 * @return
	 * @throws SmsException
	 */
	String sendVerificationCode(String phoneNumber, String host, SmsTemplate smsTemplate) throws SmsException;

	/**
	 * 校验
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param smsTemplate
	 * @throws SmsException
	 */
	void verify(String phoneNumber, String code, SmsTemplate smsTemplate) throws SmsException;
}
