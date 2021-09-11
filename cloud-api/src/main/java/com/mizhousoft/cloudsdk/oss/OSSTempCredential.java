package com.mizhousoft.cloudsdk.oss;

import java.util.Set;

import com.mizhousoft.cloudsdk.TempCredential;

/**
 * 临时凭证
 *
 * @version
 */
public class OSSTempCredential extends TempCredential
{
	// 桶
	private String bucketName;

	// 区域
	private String region;

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
