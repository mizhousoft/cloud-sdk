package com.mizhousoft.cloudsdk.oss;

/**
 * 配置
 *
 * @version
 */
public class OSSProfile
{
	// bucketName
	private String bucketName;

	// Access key
	private String accessKey;

	// Secret key
	private volatile String secretKey;

	// 区域
	private String region;

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
	 * 获取accessKey
	 * 
	 * @return
	 */
	public String getAccessKey()
	{
		return accessKey;
	}

	/**
	 * 设置accessKey
	 * 
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey)
	{
		this.accessKey = accessKey;
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
