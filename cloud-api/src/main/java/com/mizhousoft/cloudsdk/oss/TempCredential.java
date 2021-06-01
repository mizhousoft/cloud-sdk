package com.mizhousoft.cloudsdk.oss;

import java.util.Set;

/**
 * 临时凭证
 *
 * @version
 */
public class TempCredential
{
	// 桶
	private String bucketName;

	// 区域
	private String region;

	// 秘钥
	private String secretId;

	// 秘钥
	private String secretKey;

	// 秘钥
	private String token;

	// 开始时间
	private long startTime;

	// 过期时间
	private long expiredTime;

	// 对象名称
	private Set<String> objectNames;

	/**
	 * 获取bucketName
	 * 
	 * @return
	 */
	public String getBucketName()
	{
		return bucketName;
	}

	/**
	 * 设置bucketName
	 * 
	 * @param bucketName
	 */
	public void setBucketName(String bucketName)
	{
		this.bucketName = bucketName;
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

	/**
	 * 获取objectNames
	 * 
	 * @return
	 */
	public Set<String> getObjectNames()
	{
		return objectNames;
	}

	/**
	 * 设置objectNames
	 * 
	 * @param objectNames
	 */
	public void setObjectNames(Set<String> objectNames)
	{
		this.objectNames = objectNames;
	}
}
