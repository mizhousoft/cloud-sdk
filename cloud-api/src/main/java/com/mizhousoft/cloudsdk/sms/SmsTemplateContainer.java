package com.mizhousoft.cloudsdk.sms;

/**
 * 短信模板容器
 *
 * @version
 */
public interface SmsTemplateContainer
{
	/**
	 * 根据模板编码获取
	 * 
	 * @param templateCode
	 * @return
	 */
	CloudSmsTemplate getByTemplateCode(String templateCode);

	/**
	 * 注册模板
	 * 
	 * @param template
	 */
	void register(CloudSmsTemplate template);

	/**
	 * 注销模板
	 * 
	 * @param templateCode
	 * @return
	 */
	CloudSmsTemplate deregister(String templateCode);
}
