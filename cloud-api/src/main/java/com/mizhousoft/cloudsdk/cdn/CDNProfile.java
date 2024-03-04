package com.mizhousoft.cloudsdk.cdn;

/**
 * CDN Profile
 *
 * @version
 */
public class CDNProfile
{
	// Endpoint
	private String endpoint;

	// URL鉴权是否启用
	private boolean authzEnable;

	// 鉴权模式
	private String authzMode;

	// Secret key
	private volatile String secretKey;

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
	 * 获取authzEnable
	 * 
	 * @return
	 */
	public boolean isAuthzEnable()
	{
		return authzEnable;
	}

	/**
	 * 设置authzEnable
	 * 
	 * @param authzEnable
	 */
	public void setAuthzEnable(boolean authzEnable)
	{
		this.authzEnable = authzEnable;
	}

	/**
	 * 获取authzMode
	 * 
	 * @return
	 */
	public String getAuthzMode()
	{
		return authzMode;
	}

	/**
	 * 设置authzMode
	 * 
	 * @param authzMode
	 */
	public void setAuthzMode(String authzMode)
	{
		this.authzMode = authzMode;
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
}
