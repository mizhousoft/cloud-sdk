package com.mizhousoft.tencent.oss;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.util.FileDigestUtils;
import com.qcloud.cos.COSClient;

/**
 * COSObjectStorageServiceImpl Test
 *
 * @version
 */
public class TestCOSObjectStorageServiceImpl
{
	private static final String ACCESSKEY = "";

	private static final String SECRETKEY = "";

	private static final String REGION = "ap-guangzhou";

	private static final String BUCKETNAME = "rop-1255592235";

	private static final String OBJECTNAME = "test/test.txt";

	private ObjectStorageService objectStoreageService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		COSProfile profile = new COSProfile();
		profile.setAccessKey(ACCESSKEY);
		profile.setSecretKey(SECRETKEY);
		profile.setRegion(REGION);

		COSClient cosClient = COSClientBuilder.build(profile);

		COSObjectStorageServiceImpl service = new COSObjectStorageServiceImpl(cosClient, profile);

		this.objectStoreageService = service;
	}

	@Test
	public void testputObject()
	{
		String filePath = TestCOSObjectStorageServiceImpl.class.getClassLoader().getResource("test.txt").getPath();
		File localFile = new File(filePath);

		try
		{
			objectStoreageService.putObject(BUCKETNAME, OBJECTNAME, localFile);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testdeleteObject()
	{
		try
		{
			objectStoreageService.deleteObject(BUCKETNAME, OBJECTNAME);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testgetObjectMetadata()
	{
		try
		{
			ObjectMetadata objectMetadata = objectStoreageService.getObjectMetadata(BUCKETNAME, OBJECTNAME);
			System.out.println(objectMetadata.getContentLength());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testgenPresignedDownloadUrl()
	{
		try
		{
			long signExpired = 60 * 1000;
			URL url = objectStoreageService.genPresignedDownloadUrl(BUCKETNAME, OBJECTNAME, signExpired);
			System.out.println(url.toString());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testgenPresignedUploadUrl()
	{
		String objectName = "/pre/testd/test.txt";
		URL url = null;

		try
		{
			String filePath = TestCOSObjectStorageServiceImpl.class.getClassLoader().getResource("test.txt").getPath();
			File localFile = new File(filePath);
			String contentMd5 = FileDigestUtils.md5AsBase64(localFile);

			long signExpired = 60 * 1000;
			url = objectStoreageService.genPresignedUploadUrl(BUCKETNAME, objectName, signExpired, contentMd5);
			System.out.println(url.toString());

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("content-md5", contentMd5);

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());

			FileInputStream fStream = new FileInputStream(localFile);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length = -1;
			while ((length = fStream.read(buffer)) != -1)
			{
				out.write(buffer, 0, length);
			}

			fStream.close();
			out.close();
			int responseCode = connection.getResponseCode();
			System.out.println("Service returned response code " + responseCode);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
		catch (ProtocolException e)
		{
			Assertions.fail(e.getMessage());
		}
		catch (IOException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
}
