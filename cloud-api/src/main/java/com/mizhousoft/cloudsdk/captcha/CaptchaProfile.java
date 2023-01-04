package com.mizhousoft.cloudsdk.captcha;

/**
 * 配置
 *
 * @version
 */
public class CaptchaProfile
{
	// Secret id
	private String secretId;

	// Secret key
	private String secretKey;

	// Endpoint
	private String endpoint;

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
}
