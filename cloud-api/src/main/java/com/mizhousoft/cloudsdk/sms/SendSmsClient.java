package com.mizhousoft.cloudsdk.sms;

import java.util.Map;

/**
 * 发送短信客户端
 *
 * @version
 */
public interface SendSmsClient
{
	/**
	 * 发送短信
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param appId
	 * @param smsTemplate
	 * @throws SmsSendException
	 */
	void send(String phoneNumber, Map<String, String> paramMap, String appId, CloudSmsTemplate smsTemplate) throws SmsSendException;

	/**
	 * 发送多个
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param appId
	 * @param smsTemplate
	 * @throws SmsSendException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String appId, CloudSmsTemplate smsTemplate) throws SmsSendException;
}
