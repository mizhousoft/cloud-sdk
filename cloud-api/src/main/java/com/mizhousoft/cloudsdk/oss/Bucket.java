package com.mizhousoft.cloudsdk.oss;

/**
 * 桶
 *
 * @version
 */
public class Bucket
{
	// 桶名
	private String bucketName;

	// 区域
	private String region;

	/**
	 * 构造函数
	 *
	 * @param bucketName
	 * @param region
	 */
	public Bucket(String bucketName, String region)
	{
		super();
		this.bucketName = bucketName;
		this.region = region;
	}

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
}
