package com.mizhousoft.boot.sms.service;

import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SmsSendException;

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
	 * @throws SmsSendException
	 */
	String sendVerificationCode(String phoneNumber, String host, CloudSmsTemplate smsTemplate) throws SmsSendException;

	/**
	 * 校验
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param smsTemplate
	 * @throws SmsSendException
	 */
	void verify(String phoneNumber, String code, CloudSmsTemplate smsTemplate) throws SmsSendException;
}
