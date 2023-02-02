package com.mizhousoft.cloudsdk.sms;

/**
 * 阿里云配置
 *
 * @version
 */
public class SmsProfile
{
	// AccessKey ID
	private String accessKeyId;

	// AccessKey Secret
	private volatile String accessKeySecret;

	// endpoint
	private String endpoint;

	// 地域
	private String region;

	/**
	 * 获取accessKeyId
	 * 
	 * @return
	 */
	public String getAccessKeyId()
	{
		return accessKeyId;
	}

	/**
	 * 设置accessKeyId
	 * 
	 * @param accessKeyId
	 */
	public void setAccessKeyId(String accessKeyId)
	{
		this.accessKeyId = accessKeyId;
	}

	/**
	 * 获取accessKeySecret
	 * 
	 * @return
	 */
	public String getAccessKeySecret()
	{
		return accessKeySecret;
	}

	/**
	 * 设置accessKeySecret
	 * 
	 * @param accessKeySecret
	 */
	public void setAccessKeySecret(String accessKeySecret)
	{
		this.accessKeySecret = accessKeySecret;
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
}
