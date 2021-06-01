package com.mizhousoft.cloudsdk.sms;

/**
 * 短信应用工程
 *
 * @version
 */
public interface SmsApplicationFactory
{
	/**
	 * 根据名称获取
	 * 
	 * @param name
	 * @return
	 */
	SmsApplicationService getByName(String name);
}
