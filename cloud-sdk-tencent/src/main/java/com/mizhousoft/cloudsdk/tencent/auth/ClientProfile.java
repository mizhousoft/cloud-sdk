package com.mizhousoft.cloudsdk.tencent.auth;

/**
 * 配置
 *
 * @version
 */
public class ClientProfile
{
	// Constants for request protocols and methods
	public static final String REQ_HTTPS = "https://";

	public static final String REQ_HTTP = "http://";

	/**
	 * The protocol used for the request. Currently, only HTTPS is valid.
	 */
	private String protocol;

	/**
	 * valid choices: zh-CN, en-US
	 */
	private Language language;

	/**
	 * 区域
	 */
	private String region;

	/**
	 * 构造函数
	 *
	 */
	public ClientProfile()
	{
		this.protocol = REQ_HTTPS;
	}

	/**
	 * 获取protocol
	 * 
	 * @return
	 */
	public String getProtocol()
	{
		return protocol;
	}

	/**
	 * 设置protocol
	 * 
	 * @param protocol
	 */
	public void setProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * 获取language
	 * 
	 * @return
	 */
	public Language getLanguage()
	{
		return language;
	}

	/**
	 * 设置language
	 * 
	 * @param language
	 */
	public void setLanguage(Language language)
	{
		this.language = language;
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

}
