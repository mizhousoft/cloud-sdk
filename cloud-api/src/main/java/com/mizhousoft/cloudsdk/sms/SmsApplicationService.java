package com.mizhousoft.cloudsdk.sms;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;

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
	 * @throws CloudSDKException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String templateCode) throws CloudSDKException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param templateCode
	 * @throws CloudSDKException
	 */
	boolean sendNotification(String phoneNumber, Map<String, String> paramMap, String templateCode) throws CloudSDKException;

	/**
	 * 发送
	 * 
	 * @param phoneNumber
	 * @param host
	 * @param templateCode
	 * @return
	 * @throws CloudSDKException
	 */
	String sendVerificationCode(String phoneNumber, String host, String templateCode) throws CloudSDKException;

	/**
	 * 校验
	 * 
	 * @param phoneNumber
	 * @param code
	 * @param templateCode
	 * @throws CloudSDKException
	 */
	void verify(String phoneNumber, String code, String templateCode) throws CloudSDKException;
}
