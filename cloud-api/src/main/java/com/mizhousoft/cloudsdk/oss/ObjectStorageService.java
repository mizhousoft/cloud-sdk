package com.mizhousoft.cloudsdk.oss;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Set;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 对象存储服务
 *
 * @version
 */
public interface ObjectStorageService
{
	/**
	 * 上传对象
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param localFile
	 * @throws CloudSDKException
	 */
	void putObject(String bucketName, String objectName, File localFile) throws CloudSDKException;

	/**
	 * 上传对象
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param input
	 * @param contentLength
	 * @param contentType
	 * @throws CloudSDKException
	 */
	void putObject(String bucketName, String objectName, InputStream input, long contentLength, String contentType)
	        throws CloudSDKException;

	/**
	 * 拷贝对象
	 * 
	 * @param sourceBucketName
	 * @param sourceKey
	 * @param destinationBucketName
	 * @param destinationKey
	 * @throws CloudSDKException
	 */
	void copyObject(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey)
	        throws CloudSDKException;

	/**
	 * 删除对象
	 * 
	 * @param bucketName
	 * @param objectName
	 * @throws CloudSDKException
	 */
	void deleteObject(String bucketName, String objectName) throws CloudSDKException;

	/**
	 * 删除对象
	 * 
	 * @param bucketName
	 * @param objectNames
	 * @throws CloudSDKException
	 */
	void deleteObjects(String bucketName, Collection<String> objectNames) throws CloudSDKException;

	/**
	 * 获取对象元数据
	 * 
	 * @param bucketName
	 * @param objectName
	 * @return
	 * @throws CloudSDKException
	 */
	ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws CloudSDKException;

	/**
	 * 获取上传临时凭证
	 * 
	 * @param bucketName
	 * @param oneDurationSeconds
	 * @param objectNames
	 * @return
	 * @throws CloudSDKException
	 */
	OSSTempCredential getUploadTempCredential(String bucketName, Set<String> objectNames, int oneDurationSeconds) throws CloudSDKException;

	/**
	 * 获取上传临时密钥
	 * 
	 * @param bucketName
	 * @param allowPrefixes
	 * @param oneDurationSeconds
	 * @return
	 * @throws CloudSDKException
	 */
	OSSTempCredential getUploadTempCredential(String bucketName, String[] allowPrefixes, int oneDurationSeconds) throws CloudSDKException;

	/**
	 * 生成一个预签名下载url
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param signExpired
	 * @return
	 * @throws CloudSDKException
	 */
	URL genPresignedDownloadUrl(String bucketName, String objectName, long signExpired) throws CloudSDKException;

	/**
	 * 生成一个预签名上传url
	 * 
	 * @param bucketName
	 * @param objectName
	 * @param signExpired
	 * @param contentMd5
	 * @return
	 * @throws CloudSDKException
	 */
	URL genPresignedUploadUrl(String bucketName, String objectName, long signExpired, String contentMd5) throws CloudSDKException;
}
