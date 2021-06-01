package com.mizhousoft.cloudsdk.sms;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;

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
	 * @param smsTemplate
	 * @throws CloudSDKException
	 */
	void send(String phoneNumber, Map<String, String> paramMap, CloudSmsTemplate smsTemplate) throws CloudSDKException;

	/**
	 * 发送多个
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param smsTemplate
	 * @throws CloudSDKException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, CloudSmsTemplate smsTemplate) throws CloudSDKException;
}
