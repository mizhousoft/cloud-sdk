package com.mizhousoft.boot.sms.service;

/**
 * 短信风控服务
 *
 * @version
 */
public interface WindControlService
{
	/**
	 * 是否能发送短信
	 * 
	 * @param phoneNumber
	 * @param host
	 * @return
	 */
	boolean canSendSms(String phoneNumber, String host);

	/**
	 * 缓存失效
	 * 
	 * @param phoneNumber
	 * @param host
	 */
	void invalidate(String phoneNumber, String host);
}
