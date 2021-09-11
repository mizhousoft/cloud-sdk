package com.mizhousoft.cloudsdk;

/**
 * 临时凭证
 *
 * @version
 */
public class TempCredential
{
	// 秘钥
	protected String secretId;

	// 秘钥
	protected String secretKey;

	// 秘钥
	protected String token;

	// 开始时间
	protected long startTime;

	// 过期时间
	protected long expiredTime;

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
	 * 获取token
	 * 
	 * @return
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * 设置token
	 * 
	 * @param token
	 */
	public void setToken(String token)
	{
		this.token = token;
	}

	/**
	 * 获取startTime
	 * 
	 * @return
	 */
	public long getStartTime()
	{
		return startTime;
	}

	/**
	 * 设置startTime
	 * 
	 * @param startTime
	 */
	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * 获取expiredTime
	 * 
	 * @return
	 */
	public long getExpiredTime()
	{
		return expiredTime;
	}

	/**
	 * 设置expiredTime
	 * 
	 * @param expiredTime
	 */
	public void setExpiredTime(long expiredTime)
	{
		this.expiredTime = expiredTime;
	}
}
