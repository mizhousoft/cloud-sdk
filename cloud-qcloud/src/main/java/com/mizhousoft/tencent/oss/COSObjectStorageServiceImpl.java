package com.mizhousoft.tencent.oss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.oss.OSSTempCredential;
import com.mizhousoft.tencent.RegionEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.CopyObjectRequest;
import com.qcloud.cos.model.DeleteObjectsRequest;
import com.qcloud.cos.model.DeleteObjectsRequest.KeyVersion;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.region.Region;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Scope;

/**
 * 对象存储服务
 *
 * @version
 */
public class COSObjectStorageServiceImpl implements ObjectStorageService
{
	private static final Logger LOG = LoggerFactory.getLogger(COSObjectStorageServiceImpl.class);

	private COSClient cosClient;

	private COSProfile profile;

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
	public void putObject(String bucketName, String objectName, File localFile) throws CloudSDKException
	{
		try
		{
			cosClient.putObject(bucketName, objectName, localFile);
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
			com.qcloud.cos.model.ObjectMetadata metadata = new com.qcloud.cos.model.ObjectMetadata();
			metadata.setContentLength(contentLength);
			metadata.setContentType(contentType);

			cosClient.putObject(bucketName, objectName, input, metadata);
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
			cosClient.copyObject(request);
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
		try
		{
			cosClient.deleteObject(bucketName, objectName);
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

		Set<String> names = new HashSet<>(objectNames);

		// 设置要删除的key列表, 最多一次删除1000个
		List<KeyVersion> keyList = new ArrayList<>();
		for (String objectName : names)
		{
			// 传入要删除的文件名
			keyList.add(new KeyVersion(objectName));
		}
		deleteObjectsRequest.setKeys(keyList);

		try
		{
			cosClient.deleteObjects(deleteObjectsRequest);
			// List<DeletedObject> deletedObjects = deleteResult.getDeletedObjects();
		}
		catch (MultiObjectDeleteException e)
		{
			// 如果部分产出成功部分失败, 返回MultiObjectDeleteException
			// List<DeletedObject> deleteObjects = mde.getDeletedObjects();
			// List<DeleteError> deleteErrors = mde.getErrors();
			throw new CloudSDKException(e.getMessage(), e);
		}
		catch (CosServiceException e)
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
	public OSSTempCredential getUploadTempCredential(String bucketName, Set<String> objectNames, int oneDurationSeconds)
	        throws CloudSDKException
	{
		TreeMap<String, Object> config = new TreeMap<String, Object>();

		// 固定密钥
		config.put("SecretId", profile.getAccessKey());
		// 固定密钥
		config.put("SecretKey", profile.getSecretKey());

		// 临时密钥有效时长，单位是秒
		int durationSeconds = oneDurationSeconds * objectNames.size();
		config.put("durationSeconds", durationSeconds);

		try
		{
			List<Scope> scopes = new ArrayList<>(5);

			for (String objectName : objectNames)
			{
				scopes.add(new Scope("name/cos:PutObject", bucketName, profile.getRegion(), objectName));
				scopes.add(new Scope("name/cos:PostObject", bucketName, profile.getRegion(), objectName));
			}

			String policy = CosStsClient.getPolicy(scopes);
			config.put("policy", policy);

			JSONObject credential = CosStsClient.getCredential(config);
			JSONObject cre = credential.getJSONObject("credentials");
			String tmpSecretId = cre.getString("tmpSecretId");
			String tmpSecretKey = cre.getString("tmpSecretKey");
			String token = cre.getString("sessionToken");
			long startTime = credential.getLong("startTime");
			long expiredTime = credential.getLong("expiredTime");

			OSSTempCredential tc = new OSSTempCredential();
			tc.setExpiredTime(expiredTime);
			tc.setSecretId(tmpSecretId);
			tc.setSecretKey(tmpSecretKey);
			tc.setToken(token);
			tc.setStartTime(startTime);
			tc.setBucketName(bucketName);
			tc.setRegion(profile.getRegion());
			tc.setObjectNames(objectNames);

			return tc;
		}
		catch (IOException e)
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

			com.qcloud.cos.model.ObjectMetadata metadata = cosClient.getObjectMetadata(bucketName, objectName);
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
	public URL genPresignedDownloadUrl(String bucketName, String objectName, long signExpired) throws CloudSDKException
	{
		try
		{
			GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethodName.GET);
			Date expirationDate = new Date(System.currentTimeMillis() + signExpired);
			req.setExpiration(expirationDate);
			URL downloadUrl = cosClient.generatePresignedUrl(req);

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
			GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethodName.PUT);
			req.setContentMd5(contentMd5);

			Date expirationDate = new Date(System.currentTimeMillis() + signExpired);
			req.setExpiration(expirationDate);
			URL downloadUrl = cosClient.generatePresignedUrl(req);

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
	public String getRegion()
	{
		return profile.getRegion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAccessKey()
	{
		return profile.getAccessKey();
	}

	/**
	 * {@inheritDoc}
	 */
	public void destory()
	{
		if (null != cosClient)
		{
			cosClient.shutdown();
		}

		LOG.info("Shutdown cos client successfully.");
	}

	public void init(COSProfile config) throws CloudSDKException
	{
		validate(config);

		COSCredentials cred = null;
		if (StringUtils.isBlank(config.getSessionToken()))
		{
			cred = new BasicCOSCredentials(config.getAccessKey(), config.getSecretKey());
		}
		else
		{
			cred = new BasicSessionCredentials(config.getAccessKey(), config.getSecretKey(), config.getSessionToken());
		}

		RegionEnum region = RegionEnum.get(config.getRegion());
		ClientConfig clientConfig = new ClientConfig(new Region(region.getValue()));
		clientConfig.setHttpProtocol(HttpProtocol.https);

		COSClient cosClient = new COSClient(cred, clientConfig);

		this.profile = config;
		this.cosClient = cosClient;

		LOG.info("Init cos client successfully.");
	}

	private void validate(COSProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("COSProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("Cos access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("Cos secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("Cos region is null.");
		}

		RegionEnum region = RegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("Cos region does not supported, region is " + profile.getRegion() + '.');
		}
	}
}
