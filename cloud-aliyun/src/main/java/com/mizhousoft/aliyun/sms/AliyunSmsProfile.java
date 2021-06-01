package com.mizhousoft.aliyun.sms;

/**
 * 阿里云配置
 *
 * @version
 */
public class AliyunSmsProfile
{
	// AccessKey ID
	private String accessKeyId;

	// AccessKey Secret
	private String accessKeySecret;

	// endpoint
	private String endpoint;

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
}
