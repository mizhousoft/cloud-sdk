package com.mizhousoft.cloudsdk.oss;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 桶存储服务
 *
 * @version
 */
public interface BucketStroageService
{
	/**
	 * 获取上传临时凭证
	 * 
	 * @param oneDurationSeconds
	 * @param objectNames
	 * @return
	 * @throws CloudSDKException
	 */
	OSSTempCredential getUploadTempCredential(int oneDurationSeconds, Set<String> objectNames) throws CloudSDKException;

	/**
	 * 获取桶信息
	 * 
	 * @return
	 */
	Bucket getBucket();

	/**
	 * 获取文件对象元数据
	 * 
	 * @param bucketName
	 * @param objectName
	 * @return
	 * @throws CloudSDKException
	 */
	ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws CloudSDKException;

	/**
	 * 获取对象下载地址
	 * 
	 * @param signExpiredMs
	 * @param objectName
	 * @return
	 */
	String getObjectDownloadUrl(long signExpiredMs, String objectName);

	/**
	 * 删除对象
	 * 
	 * @param bucketName
	 * @param objectName
	 * @throws CloudSDKException
	 */
	void delete(String bucketName, String objectName) throws CloudSDKException;

	/**
	 * 删除对象
	 * 
	 * @param bucketName
	 * @param objectNames
	 * @throws CloudSDKException
	 */
	void delete(String bucketName, Collection<String> objectNames) throws CloudSDKException;

	/**
	 * 上传对象
	 * 
	 * @param objectName
	 * @param localFile
	 * @throws CloudSDKException
	 */
	void putObject(String objectName, File localFile) throws CloudSDKException;

	/**
	 * 获取对象存储服务
	 * 
	 * @return
	 */
	ObjectStorageService getObjectStorageService();

	/**
	 * 销毁
	 */
	void destory();
}
