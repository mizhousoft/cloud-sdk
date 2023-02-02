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
	private boolean urlAuthzEnable;

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
	 * 获取urlAuthzEnable
	 * 
	 * @return
	 */
	public boolean isUrlAuthzEnable()
	{
		return urlAuthzEnable;
	}

	/**
	 * 设置urlAuthzEnable
	 * 
	 * @param urlAuthzEnable
	 */
	public void setUrlAuthzEnable(boolean urlAuthzEnable)
	{
		this.urlAuthzEnable = urlAuthzEnable;
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
