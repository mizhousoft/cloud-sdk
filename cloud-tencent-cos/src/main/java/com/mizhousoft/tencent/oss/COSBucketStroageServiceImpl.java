package com.mizhousoft.tencent.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;
import com.mizhousoft.cloudsdk.oss.Bucket;
import com.mizhousoft.cloudsdk.oss.BucketStroageService;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.tencent.cdn.TencentCDNSignServiceImpl;
import com.qcloud.cos.COSClient;

/**
 * 桶存储服务
 *
 * @version
 */
public class COSBucketStroageServiceImpl implements BucketStroageService
{
	private static final Logger LOG = LoggerFactory.getLogger(COSBucketStroageServiceImpl.class);

	// 对象存储服务
	protected ObjectStorageService objectStorageService;

	// CDN服务
	protected CDNSignService cdnSignService;

	// COSProfile
	protected volatile COSProfile cosProfile;

	// COSClient
	protected COSClient cosClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bucket getBucket()
	{
		Bucket bucket = new Bucket(cosProfile.getBucketName(), cosProfile.getRegion());

		return bucket;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRegion()
	{
		return cosProfile.getRegion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAccessKey()
	{
		return cosProfile.getAccessKey();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean doesBucketExist(String bucketName) throws CloudSDKException
	{
		try
		{
			return cosClient.doesBucketExist(bucketName);
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
	public void destroy()
	{
		if (null != cosClient)
		{
			cosClient.shutdown();

			LOG.info("Shutdown {} cos client successfully.", cosProfile.getBucketName());
		}
	}

	/**
	 * 初始化
	 * 
	 * @param cosProfile
	 * @param cdnProfile
	 * @throws CloudSDKException
	 */
	public void init(COSProfile cosProfile, CDNProfile cdnProfile) throws CloudSDKException
	{
		if (null == cosProfile || null == cosProfile.getBucketName())
		{
			throw new CloudSDKException("Bucket name is null.");
		}

		this.cosClient = COSClientBuilder.build(cosProfile);
		this.cosProfile = cosProfile;

		COSObjectStorageServiceImpl objectStorageService = new COSObjectStorageServiceImpl(this.cosClient, cosProfile);
		this.objectStorageService = objectStorageService;

		if (null != cdnProfile)
		{
			TencentCDNSignServiceImpl cdnSignService = new TencentCDNSignServiceImpl(cdnProfile);
			this.cdnSignService = cdnSignService;
		}

		LOG.info("Init {} cos client successfully.", cosProfile.getBucketName());
	}
}
