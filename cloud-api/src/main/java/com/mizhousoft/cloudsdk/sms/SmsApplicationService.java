package com.mizhousoft.cloudsdk.sms;

import java.util.Map;

/**
 * 短信应用服务
 *
 * @version
 */
public interface SmsApplicationService
{
	/**
	 * 发送
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param templateCode
	 * @throws SmsSendException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String templateCode) throws SmsSendException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param templateCode
	 * @throws SmsSendException
	 */
	boolean sendNotification(String phoneNumber, Map<String, String> paramMap, String templateCode) throws SmsSendException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param host
	 * @param templateCode
	 * @return
	 * @throws SmsSendException
	 */
	String sendVerificationCode(String phoneNumber, String host, String templateCode) throws SmsSendException;

	/**
	 * 校验
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param templateCode
	 * @throws SmsSendException
	 */
	void verify(String phoneNumber, String code, String templateCode) throws SmsSendException;
}
