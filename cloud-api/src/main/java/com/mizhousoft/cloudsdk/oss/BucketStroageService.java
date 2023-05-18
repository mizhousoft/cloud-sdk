package com.mizhousoft.cloudsdk.oss;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;

/**
 * 桶存储服务
 *
 * @version
 */
public interface BucketStroageService
{
	/**
	 * 获取桶信息
	 * 
	 * @return
	 */
	Bucket getBucket();

	/**
	 * 获取区域
	 * 
	 * @return
	 */
	String getRegion();

	/**
	 * 获取AccessKey
	 * 
	 * @return
	 */
	String getAccessKey();

	/**
	 * 检索存储桶是否存在且是否有权限访问
	 * 
	 * @param bucketName
	 * @return
	 * @throws CloudSDKException
	 */
	boolean doesBucketExist(String bucketName) throws CloudSDKException;

	/**
	 * 获取对象存储服务
	 * 
	 * @return
	 */
	ObjectStorageService getObjectStorageService();

	/**
	 * 获取CDNSignService
	 * 
	 * @return
	 */
	CDNSignService getCDNSignService();

	/**
	 * 销毁
	 */
	void destroy();
}
