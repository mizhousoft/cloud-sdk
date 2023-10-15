package com.mizhousoft.boot.sms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SMS应用配置
 *
 * @version
 */
@Component
@ConfigurationProperties(prefix = "sms.application")
public class SmsApplication
{
	// 应用名称，标识唯一
	private String name;

	// 供应商
	private String vendor;

	// 应用ID
	private String appId;

	// 密钥ID
	private String secretId;

	// 密钥key
	private volatile String secretKey;

	// endpoint
	private String endpoint;

	// 地域
	private String region;

	// 编码
	private String nationCode;

	// 限流是否启用
	private boolean limitEnable;

	// 一个IP一天最大请求数
	private int hostMaxRequest;

	/**
	 * 获取name
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 获取vendor
	 * 
	 * @return
	 */
	public String getVendor()
	{
		return vendor;
	}

	/**
	 * 设置vendor
	 * 
	 * @param vendor
	 */
	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

	/**
	 * 获取appId
	 * 
	 * @return
	 */
	public String getAppId()
	{
		return appId;
	}

	/**
	 * 设置appId
	 * 
	 * @param appId
	 */
	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	/**
	 * 获取secretId
	 * 
	 * @return
	 */
	public String getSecretId()
	{
		return secretId;
	}

	/**
	 * 设置secretId
	 * 
	 * @param secretId
	 */
	public void setSecretId(String secretId)
	{
		this.secretId = secretId;
	}

	/**
	 * 获取secretKey
	 * 
	 * @return
	 */
	public String getSecretKey()
	{
		return secretKey;
	}

	/**
	 * 设置secretKey
	 * 
	 * @param secretKey
	 */
	public void setSecretKey(String secretKey)
	{
		this.secretKey = secretKey;
	}

	/**
	 * 获取endpoint
	 * 
	 * @return
	 */
	public String getEndpoint()
	{
		return endpoint;
	}

	/**
	 * 设置endpoint
	 * 
	 * @param endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		this.endpoint = endpoint;
	}

	/**
	 * 获取region
	 * 
	 * @return
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * 设置region
	 * 
	 * @param region
	 */
	public void setRegion(String region)
	{
		this.region = region;
	}

	/**
	 * 获取nationCode
	 * 
	 * @return
	 */
	public String getNationCode()
	{
		return nationCode;
	}

	/**
	 * 设置nationCode
	 * 
	 * @param nationCode
	 */
	public void setNationCode(String nationCode)
	{
		this.nationCode = nationCode;
	}

	/**
	 * 获取limitEnable
	 * 
	 * @return
	 */
	public boolean isLimitEnable()
	{
		return limitEnable;
	}

	/**
	 * 设置limitEnable
	 * 
	 * @param limitEnable
	 */
	public void setLimitEnable(boolean limitEnable)
	{
		this.limitEnable = limitEnable;
	}

	/**
	 * 获取hostMaxRequest
	 * 
	 * @return
	 */
	public int getHostMaxRequest()
	{
		return hostMaxRequest;
	}

	/**
	 * 设置hostMaxRequest
	 * 
	 * @param hostMaxRequest
	 */
	public void setHostMaxRequest(int hostMaxRequest)
	{
		this.hostMaxRequest = hostMaxRequest;
	}
}
