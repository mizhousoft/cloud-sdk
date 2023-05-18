package com.mizhousoft.cloudsdk.oss;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;

/**
 * 对象存储客户端
 *
 */
public interface OSSClient extends ObjectStorageService
{
	/**
	 * 获取桶名
	 * 
	 * @param identifier
	 * @return
	 */
	String getBucketName(String identifier);

	/**
	 * 获取桶服务
	 * 
	 * @param bucketName
	 * @return
	 */
	BucketStroageService getBucketService(String bucketName);

	/**
	 * 获取桶服务
	 * 
	 * @param identifier
	 * @return
	 */
	BucketStroageService getBucketServiceByIdentifier(String identifier);

	/**
	 * 获取对象存储服务
	 * 
	 * @param bucketName
	 * @return
	 */
	ObjectStorageService getObjectService(String bucketName);

	/**
	 * 获取对象存储服务
	 * 
	 * @param identifier
	 * @return
	 */
	ObjectStorageService getObjectServiceByIdentifier(String identifier);

	/**
	 * 获取CDNSignService
	 * 
	 * @param bucketName
	 * @return
	 */
	CDNSignService getCDNService(String bucketName);

	/**
	 * 获取CDNSignService
	 * 
	 * @param identifier
	 * @return
	 */
	CDNSignService getCDNServiceByIdentifier(String identifier);

	/**
	 * 增加桶服务
	 * 
	 * @param identifier
	 * @param ossProfile
	 * @throws CloudSDKException
	 */
	void addBucketService(String identifier, OSSProfile ossProfile) throws CloudSDKException;

	/**
	 * 增加桶服务
	 * 
	 * @param identifier
	 * @param ossProfile
	 * @param cdnProfile
	 * @throws CloudSDKException
	 */
	void addBucketService(String identifier, OSSProfile ossProfile, CDNProfile cdnProfile) throws CloudSDKException;

	/**
	 * 删除桶服务
	 * 
	 * @param bucketName
	 */
	void removeBucketService(String bucketName);

	/**
	 * 获取对象下载地址
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param signExpiredMs
	 * @return
	 */
	String getCDNDownloadUrl(String bucketName, String objectName, long signExpiredMs);

	/**
	 * 获取对象下载地址
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param signExpiredMs
	 * @param params
	 * @return
	 */
	String getCDNDownloadUrl(String bucketName, String objectName, long signExpiredMs, WaterMarkParams params);

	/**
	 * 销毁
	 */
	void destroy();
}
