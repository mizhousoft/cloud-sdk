package com.mizhousoft.cloudsdk.sms2;

/**
 * 短信应用工厂类
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
	SmsApplicationClient getByName(String name);
}
