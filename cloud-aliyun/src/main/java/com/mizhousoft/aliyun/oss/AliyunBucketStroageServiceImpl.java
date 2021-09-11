package com.mizhousoft.aliyun.oss;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.aliyun.cdn.AliyunCDNSignServiceImpl;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;
import com.mizhousoft.cloudsdk.oss.Bucket;
import com.mizhousoft.cloudsdk.oss.BucketStroageService;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.oss.OSSTempCredential;

/**
 * 存储和CDN存储混合服务
 *
 * @version
 */
public class AliyunBucketStroageServiceImpl implements BucketStroageService
{
	private static final Logger LOG = LoggerFactory.getLogger(AliyunBucketStroageServiceImpl.class);

	// 对象存储服务
	protected ObjectStorageService objectStorageService;

	// CDN服务
	protected CDNSignService cdnSignService;

	// 桶名
	protected String bucketName;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OSSTempCredential getUploadTempCredential(int oneDurationSeconds, Set<String> objectNames) throws CloudSDKException
	{
		return objectStorageService.getUploadTempCredential(bucketName, objectNames, oneDurationSeconds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bucket getBucket()
	{
		Bucket bucket = new Bucket();
		bucket.setBucketName(bucketName);
		bucket.setRegion(objectStorageService.getRegion());

		return bucket;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws CloudSDKException
	{
		try
		{
			return objectStorageService.getObjectMetadata(bucketName, objectName);
		}
		catch (CloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getObjectDownloadUrl(long signExpiredMs, String objectName)
	{
		return cdnSignService.signUrl(objectName, signExpiredMs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String bucketName, String objectName) throws CloudSDKException
	{
		if (StringUtils.isBlank(bucketName) || StringUtils.isBlank(objectName))
		{
			LOG.warn("Object can not delete, bucket name is {}, object name is {}.", bucketName, objectName);
			return;
		}

		try
		{
			objectStorageService.deleteObject(bucketName, objectName);
		}
		catch (CloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getCodeParams(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String bucketName, Collection<String> objectNames) throws CloudSDKException
	{
		if (StringUtils.isBlank(bucketName) || CollectionUtils.isEmpty(objectNames))
		{
			LOG.warn("Object can not delete, bucket name is {}, object names is {}.", bucketName, objectNames);
			return;
		}

		try
		{
			objectStorageService.deleteObjects(bucketName, objectNames);
		}
		catch (CloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getCodeParams(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putObject(String objectName, File localFile) throws CloudSDKException
	{
		try
		{
			objectStorageService.putObject(bucketName, objectName, localFile);
		}
		catch (CloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getCodeParams(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectStorageService getObjectStorageService()
	{
		return objectStorageService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destory()
	{
		if (null != objectStorageService)
		{
			objectStorageService.destory();
		}
	}

	public void init(String bucketName, AliyunOSSProfile ossProfile, CDNProfile cdnProfile) throws CloudSDKException
	{
		this.bucketName = bucketName;

		AliyunObjectStorageServiceImpl objectStorageService = new AliyunObjectStorageServiceImpl();
		objectStorageService.init(ossProfile);
		this.objectStorageService = objectStorageService;

		AliyunCDNSignServiceImpl cdnSignService = new AliyunCDNSignServiceImpl();
		cdnSignService.init(cdnProfile);
		this.cdnSignService = cdnSignService;
	}
}
