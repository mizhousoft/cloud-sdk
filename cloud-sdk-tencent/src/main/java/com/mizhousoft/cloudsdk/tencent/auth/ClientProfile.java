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
	 * The endpoint for the API request (e.g., "cvm.tencentcloudapi.com").
	 */
	private String endpoint;

	/**
	 * The protocol used for the request. Currently, only HTTPS is valid.
	 */
	private String protocol;

	/**
	 * valid choices: zh-CN, en-US
	 */
	private Language language;

	/**
	 * 版本
	 */
	private String version;

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
		this.endpoint = null;
		this.protocol = REQ_HTTPS;
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
	 * 获取version
	 * 
	 * @return
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * 设置version
	 * 
	 * @param version
	 */
	public void setVersion(String version)
	{
		this.version = version;
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
