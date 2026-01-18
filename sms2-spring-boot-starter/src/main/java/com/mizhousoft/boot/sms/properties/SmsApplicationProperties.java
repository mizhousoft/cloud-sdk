package com.mizhousoft.boot.sms.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 *
 * @version
 */
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsApplicationProperties
{
	/**
	 * 应用
	 */
	private List<SmsApplication> applications;

	/**
	 * 获取applications
	 * 
	 * @return
	 */
	public List<SmsApplication> getApplications()
	{
		return applications;
	}

	/**
	 * 设置applications
	 * 
	 * @param applications
	 */
	public void setApplications(List<SmsApplication> applications)
	{
		this.applications = applications;
	}
}
