package com.mizhousoft.boot.sms.service;

/**
 * 短信风控服务
 *
 * @version
 */
public interface SmsWindControlService
{
	/**
	 * 判断请求是否正常
	 * 
	 * @param phoneNumber
	 * @param host
	 * @return
	 */
	boolean determineRequestOk(String phoneNumber, String host);

	/**
	 * 缓存失效
	 * 
	 * @param phoneNumber
	 * @param host
	 */
	void invalidate(String phoneNumber, String host);
}
