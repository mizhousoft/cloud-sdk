package com.mizhousoft.aliyun.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSS;
import com.mizhousoft.aliyun.cdn.AliyunCDNSignServiceImpl;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;
import com.mizhousoft.cloudsdk.oss.Bucket;
import com.mizhousoft.cloudsdk.oss.BucketStroageService;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;

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

	private OSS ossClient;

	private AliyunOSSProfile ossProfile;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bucket getBucket()
	{
		Bucket bucket = new Bucket(ossProfile.getBucketName(), ossProfile.getRegion());

		return bucket;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRegion()
	{
		return ossProfile.getRegion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAccessKey()
	{
		return ossProfile.getAccessKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean doesBucketExist(String bucketName) throws CloudSDKException
	{
		try
		{
			return ossClient.doesBucketExist(bucketName);
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
	public ObjectStorageService getObjectStorageService()
	{
		return objectStorageService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CDNSignService getCDNSignService()
	{
		return cdnSignService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy()
	{
		if (null != ossClient)
		{
			ossClient.shutdown();
		}

		LOG.info("Shutdown oss client successfully.");
	}

	public void init(String bucketName, AliyunOSSProfile ossProfile, CDNProfile cdnProfile) throws CloudSDKException
	{
		if (null == ossProfile || null == ossProfile.getBucketName())
		{
			throw new CloudSDKException("Bucket name is null.");
		}

		this.ossClient = AliyunOSSClientBuilder.build(ossProfile);
		this.ossProfile = ossProfile;

		AliyunObjectStorageServiceImpl objectStorageService = new AliyunObjectStorageServiceImpl(this.ossClient, this.ossProfile);
		this.objectStorageService = objectStorageService;

		if (null != cdnProfile)
		{
			AliyunCDNSignServiceImpl cdnSignService = new AliyunCDNSignServiceImpl(cdnProfile);
			this.cdnSignService = cdnSignService;
		}

		LOG.info("Init {} oss client successfully.", ossProfile.getBucketName());
	}
}
