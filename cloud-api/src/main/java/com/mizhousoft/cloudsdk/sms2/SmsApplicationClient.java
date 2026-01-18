package com.mizhousoft.cloudsdk.sms2;

import java.util.Map;

/**
 * 短信应用客户端
 *
 * @version
 */
public interface SmsApplicationClient
{
	/**
	 * 发送
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param templateCode
	 * @throws SmsException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String templateCode) throws SmsException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param templateCode
	 * @throws SmsException
	 */
	boolean sendNotification(String phoneNumber, Map<String, String> paramMap, String templateCode) throws SmsException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param host
	 * @param templateCode
	 * @return
	 * @throws SmsException
	 */
	String sendVerificationCode(String phoneNumber, String host, String templateCode) throws SmsException;

	/**
	 * 校验
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param templateCode
	 * @throws SmsException
	 */
	void verify(String phoneNumber, String code, String templateCode) throws SmsException;

	/**
	 * 获取短信模板
	 * 
	 * @param templateCode
	 * @return
	 * @throws SmsException
	 */
	SmsTemplate getSmsTemplate(String templateCode) throws SmsException;

	/**
	 * 增加短信模板
	 * 
	 * @param template
	 */
	void addSmsTemplate(SmsTemplate template);

	/**
	 * 移除短信模板
	 * 
	 * @param templateCode
	 * @return
	 */
	SmsTemplate removeSmsTemplate(String templateCode);
}
