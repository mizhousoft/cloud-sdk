package com.mizhousoft.tencent.oss;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.cdn.CDNSignService;
import com.mizhousoft.cloudsdk.oss.BucketStroageService;
import com.mizhousoft.cloudsdk.oss.OSSClient;
import com.mizhousoft.cloudsdk.oss.OSSProfile;
import com.mizhousoft.cloudsdk.oss.OSSTempCredential;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.oss.WaterMarkParams;
import com.mizhousoft.commons.data.NestedRuntimeException;

/**
 * 对象存储客户端
 *
 */
public class TencentOSSClient implements OSSClient
{
	/**
	 * 桶服务<BucketName, BucketStroageService>
	 */
	private Map<String, BucketStroageService> bucketServiceMap = new ConcurrentHashMap<>(5);

	/**
	 * <Identifier, BucketName>
	 */
	private Map<String, String> bucketNameMap = new ConcurrentHashMap<>(5);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBucketName(String identifier)
	{
		String bucketName = bucketNameMap.get(identifier);
		if (null == bucketName)
		{
			throw new NestedRuntimeException("BucketName not found, identifier is " + identifier);
		}

		return bucketName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BucketStroageService getBucketService(String bucketName)
	{
		BucketStroageService bucketService = bucketServiceMap.get(bucketName);
		if (null == bucketService)
		{
			throw new NestedRuntimeException("BucketStroageService not found, bucket name is " + bucketName);
		}

		return bucketService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BucketStroageService getBucketServiceByIdentifier(String identifier)
	{
		String bucketName = getBucketName(identifier);

		return getBucketService(bucketName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectStorageService getObjectService(String bucketName)
	{
		BucketStroageService bucketService = getBucketService(bucketName);

		return bucketService.getObjectStorageService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectStorageService getObjectServiceByIdentifier(String identifier)
	{
		String bucketName = getBucketName(identifier);
		BucketStroageService bucketService = getBucketService(bucketName);

		return bucketService.getObjectStorageService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CDNSignService getCDNService(String bucketName)
	{
		BucketStroageService bucketService = getBucketService(bucketName);

		return bucketService.getCDNSignService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CDNSignService getCDNServiceByIdentifier(String identifier)
	{
		String bucketName = getBucketName(identifier);
		BucketStroageService bucketService = getBucketService(bucketName);

		return bucketService.getCDNSignService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addBucketService(String identifier, OSSProfile ossProfile) throws CloudSDKException
	{
		addBucketService(identifier, ossProfile, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addBucketService(String identifier, OSSProfile ossProfile, CDNProfile cdnProfile) throws CloudSDKException
	{
		if (ossProfile instanceof COSProfile)
		{
			COSBucketStroageServiceImpl bucketService = new COSBucketStroageServiceImpl();
			bucketService.init((COSProfile) ossProfile, cdnProfile);

			bucketServiceMap.put(ossProfile.getBucketName(), bucketService);
			bucketNameMap.put(identifier, ossProfile.getBucketName());
		}
		else
		{
			throw new CloudSDKException("OSSProfile is not COSProfile.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeBucketService(String bucketName)
	{
		BucketStroageService bucketService = bucketServiceMap.remove(bucketName);
		if (null != bucketService)
		{
			bucketService.destroy();
		}

		Iterator<Entry<String, String>> iter = bucketNameMap.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<String, String> entry = iter.next();
			if (entry.getValue().equals(bucketName))
			{
				iter.remove();
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCDNDownloadUrl(String bucketName, String objectName, long signExpiredMs)
	{
		CDNSignService cdnSignService = getCDNService(bucketName);
		if (null == cdnSignService)
		{
			throw new NestedRuntimeException("Method not support.");
		}

		return cdnSignService.signUrl(objectName, signExpiredMs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCDNDownloadUrl(String bucketName, String objectName, long signExpiredMs, WaterMarkParams params)
	{
		CDNSignService cdnSignService = getCDNService(bucketName);
		if (null == cdnSignService)
		{
			throw new NestedRuntimeException("Method not support.");
		}

		String downloadUrl = cdnSignService.signUrl(objectName, signExpiredMs);

		String waterMarkPath = WaterMarkUriBuilder.build(params);
		downloadUrl = downloadUrl + "&" + waterMarkPath;

		return downloadUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy()
	{
		Iterator<Entry<String, BucketStroageService>> iter = bucketServiceMap.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<String, BucketStroageService> entry = iter.next();

			BucketStroageService bucketService = entry.getValue();
			bucketService.destroy();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putObject(String bucketName, String objectName, File localFile) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		objectStorageService.putObject(bucketName, objectName, localFile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putObject(String bucketName, String objectName, InputStream input, long contentLength, String contentType)
	        throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		objectStorageService.putObject(bucketName, objectName, input, contentLength, contentType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyObject(String sourceBucketName, String sourceKey, String destinationBucketName, String destinationKey)
	        throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(sourceBucketName);

		objectStorageService.copyObject(sourceBucketName, sourceKey, destinationBucketName, destinationKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteObject(String bucketName, String objectName) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		objectStorageService.deleteObject(bucketName, objectName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteObjects(String bucketName, Collection<String> objectNames) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		objectStorageService.deleteObjects(bucketName, objectNames);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ObjectMetadata getObjectMetadata(String bucketName, String objectName) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		return objectStorageService.getObjectMetadata(bucketName, objectName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OSSTempCredential getUploadTempCredential(String bucketName, Set<String> objectNames, int oneDurationSeconds)
	        throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		return objectStorageService.getUploadTempCredential(bucketName, objectNames, oneDurationSeconds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OSSTempCredential getUploadTempCredential(String bucketName, String[] allowPrefixes, int oneDurationSeconds)
	        throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		return objectStorageService.getUploadTempCredential(bucketName, allowPrefixes, oneDurationSeconds);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL genPresignedDownloadUrl(String bucketName, String objectName, long signExpired) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		return objectStorageService.genPresignedDownloadUrl(bucketName, objectName, signExpired);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL genPresignedUploadUrl(String bucketName, String objectName, long signExpired, String contentMd5) throws CloudSDKException
	{
		ObjectStorageService objectStorageService = getObjectService(bucketName);

		return objectStorageService.genPresignedUploadUrl(bucketName, objectName, signExpired, contentMd5);
	}
}
