package com.mizhousoft.aliyun.oss;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.auth.sts.AssumeRoleResponse.Credentials;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.oss.OSSTempCredential;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;

/**
 * 对象存储服务
 *
 * @version
 */
public class AliyunObjectStorageServiceImpl implements ObjectStorageService
{
	private static final Logger LOG = LoggerFactory.getLogger(AliyunObjectStorageServiceImpl.class);

	private OSS ossClient;

	private AliyunOSSProfile profile;

	/**
	 * 构造函数
	 *
	 * @param ossClient
	 * @param profile
	 */
	public AliyunObjectStorageServiceImpl(OSS ossClient, AliyunOSSProfile profile)
	{
		super();
		this.ossClient = ossClient;
		this.profile = profile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putObject(String bucketName, String objectName, File localFile) throws CloudSDKException
	{
		try
		{
			ossClient.putObject(bucketName, objectName, localFile);
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putObject(String bucketName, String objectName, InputStream input, long contentLength, String contentType)
	        throws CloudSDKException
	{
		try
		{
			com.aliyun.oss.model.ObjectMetadata metadata = new com.aliyun.oss.model.ObjectMetadata();
			metadata.setContentLength(contentLength);
			metadata.setContentType(contentType);

			ossClient.putObject(bucketName, objectName, input, metadata);
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Throwable e)
			{
				LOG.error("Close file input stream failed.", e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyObject(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey)
	        throws CloudSDKException
	{
		try
		{
			CopyObjectRequest request = new CopyObjectRequest(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
			ossClient.copyObject(request);
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteObject(String bucketName, String objectName) throws CloudSDKException
	{
		if (StringUtils.isBlank(bucketName) || StringUtils.isBlank(objectName))
		{
			LOG.warn("Object can not delete, bucket name is {}, object name is {}.", bucketName, objectName);
			return;
		}

		try
		{
			ossClient.deleteObject(bucketName, objectName);
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteObjects(String bucketName, Collection<String> objectNames) throws CloudSDKException
	{
		if (StringUtils.isBlank(bucketName) || CollectionUtils.isEmpty(objectNames))
		{
			LOG.warn("Object can not delete, bucket name is {}, object names is {}.", bucketName, objectNames);
			return;
		}

		List<String> list = new ArrayList<>(objectNames);

		// 设置要删除的key列表, 最多一次删除1000个
		int toIndex = 0;
		int fromIndex = 0;
		while (true)
		{
			toIndex = toIndex + 1000;
			fromIndex = toIndex - 1000;
			if (fromIndex > objectNames.size())
			{
				break;
			}

			List<String> subList = null;
			if (toIndex > objectNames.size())
			{
				subList = list.subList(fromIndex, objectNames.size());
			}
			else
			{
				subList = list.subList(fromIndex, toIndex);
			}

			doDeleteObjects(bucketName, subList);
		}
	}

	private void doDeleteObjects(String bucketName, Collection<String> objectNames) throws CloudSDKException
	{
		DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);

		List<String> keyList = new ArrayList<>(objectNames);
		deleteObjectsRequest.setKeys(keyList);

		try
		{
			ossClient.deleteObjects(deleteObjectsRequest);
			// List<DeletedObject> deletedObjects = deleteResult.getDeletedObjects();
		}
		catch (OSSException e)
		{
			// 如果部分产出成功部分失败, 返回MultiObjectDeleteException
			// List<DeletedObject> deleteObjects = mde.getDeletedObjects();
			// List<DeleteError> deleteErrors = mde.getErrors();
			throw new CloudSDKException(e.getMessage(), e);
		}
		catch (ClientException e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws CloudSDKException
	{
		try
		{
			ObjectMetadata objectMetadata = null;

			com.aliyun.oss.model.ObjectMetadata metadata = ossClient.getObjectMetadata(bucketName, objectName);
			if (null != metadata)
			{
				objectMetadata = new ObjectMetadata();
				objectMetadata.setContentLength(metadata.getContentLength());
				objectMetadata.setContentMD5(metadata.getContentMD5());
				objectMetadata.setContentType(metadata.getContentType());
				objectMetadata.setLastModified(metadata.getLastModified());
				objectMetadata.setObjectName(objectName);

				return objectMetadata;
			}
			else
			{
				throw new CloudSDKException("ObjectName not found.");
			}
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OSSTempCredential getUploadTempCredential(String bucketName, Set<String> objectNames, int oneDurationSeconds)
	        throws CloudSDKException
	{
		String policy = "{\"Version\":\"1\",\"Statement\":[{\"Effect\":\"Allow\",\"Action\":[\"oss:PutObject\"],\"Resource\":[\"acs:oss:*:*:*\"]}]}";

		try
		{
			// 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
			DefaultProfile.addEndpoint("", "Sts", profile.getStsEndpoint());
			// 构造default profile（参数留空，无需添加region ID）
			IClientProfile clientProfile = DefaultProfile.getProfile("", profile.getAccessKey(), profile.getSecretKey());
			// 用profile构造client
			DefaultAcsClient client = new DefaultAcsClient(clientProfile);
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setSysMethod(MethodType.POST);
			request.setRoleArn(profile.getRoleArn());
			request.setRoleSessionName(profile.getRoleSessionName());
			request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
			request.setDurationSeconds((long) oneDurationSeconds); // 设置凭证有效时间

			final AssumeRoleResponse response = client.getAcsResponse(request);

			Credentials credentials = response.getCredentials();

			OSSTempCredential tc = new OSSTempCredential();
			tc.setSecretId(credentials.getAccessKeyId());
			tc.setSecretKey(credentials.getAccessKeySecret());
			tc.setToken(credentials.getSecurityToken());
			tc.setBucketName(bucketName);
			tc.setObjectNames(objectNames);
			tc.setRegion(profile.getRegion());

			return tc;
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OSSTempCredential getUploadTempCredential(String bucketName, String[] allowPrefixes, int oneDurationSeconds)
	        throws CloudSDKException
	{
		String policy = "{\"Version\":\"1\",\"Statement\":[{\"Effect\":\"Allow\",\"Action\":[\"oss:PutObject\"],\"Resource\":[\"acs:oss:*:*:*\"]}]}";

		try
		{
			// 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
			DefaultProfile.addEndpoint("", "Sts", profile.getStsEndpoint());
			// 构造default profile（参数留空，无需添加region ID）
			IClientProfile clientProfile = DefaultProfile.getProfile("", profile.getAccessKey(), profile.getSecretKey());
			// 用profile构造client
			DefaultAcsClient client = new DefaultAcsClient(clientProfile);
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setSysMethod(MethodType.POST);
			request.setRoleArn(profile.getRoleArn());
			request.setRoleSessionName(profile.getRoleSessionName());
			request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
			request.setDurationSeconds((long) oneDurationSeconds); // 设置凭证有效时间

			final AssumeRoleResponse response = client.getAcsResponse(request);

			Credentials credentials = response.getCredentials();

			OSSTempCredential tc = new OSSTempCredential();
			tc.setSecretId(credentials.getAccessKeyId());
			tc.setSecretKey(credentials.getAccessKeySecret());
			tc.setToken(credentials.getSecurityToken());
			tc.setBucketName(bucketName);
			tc.setRegion(profile.getRegion());

			return tc;
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL genPresignedDownloadUrl(String bucketName, String objectName, long signExpired) throws CloudSDKException
	{
		try
		{
			GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
			Date expirationDate = new Date(System.currentTimeMillis() + signExpired);
			req.setExpiration(expirationDate);
			URL downloadUrl = ossClient.generatePresignedUrl(req);

			return downloadUrl;
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL genPresignedUploadUrl(String bucketName, String objectName, long signExpired, String contentMd5) throws CloudSDKException
	{
		try
		{
			GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.PUT);
			req.setContentMD5(contentMd5);

			Date expirationDate = new Date(System.currentTimeMillis() + signExpired);
			req.setExpiration(expirationDate);
			URL downloadUrl = ossClient.generatePresignedUrl(req);

			return downloadUrl;
		}
		catch (Throwable e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

}
