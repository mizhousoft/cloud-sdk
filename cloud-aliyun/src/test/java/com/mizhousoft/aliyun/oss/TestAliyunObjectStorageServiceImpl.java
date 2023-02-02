package com.mizhousoft.aliyun.oss;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.oss.ObjectMetadata;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.util.FileDigestUtils;

/**
 * AliyunObjectStorageServiceImpl Test
 *
 * @version
 */
public class TestAliyunObjectStorageServiceImpl
{
	private static final String ACCESSKEY = "";

	private static final String SECRETKEY = "";

	private static final String ENDPOINT = "";

	private static final String STS_ENDPOINT = "";

	private static final String BUCKETNAME = "";

	private static final String OBJECTNAME = "test/test.txt";

	private ObjectStorageService objectStoreageService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		AliyunOSSProfile profile = new AliyunOSSProfile();
		profile.setAccessKey(ACCESSKEY);
		profile.setSecretKey(SECRETKEY);
		profile.setEndpoint(ENDPOINT);
		profile.setStsEndpoint(STS_ENDPOINT);
		profile.setRoleArn("acs:ram::30394992:role/ramosstest");
		profile.setRoleSessionName("web-service3");
		profile.setRegion("oss-cn-shenzhen");

		AliyunObjectStorageServiceImpl service = new AliyunObjectStorageServiceImpl();
		service.init(profile);

		this.objectStoreageService = service;
	}

	@Test
	public void testputObject()
	{
		String filePath = TestAliyunObjectStorageServiceImpl.class.getClassLoader().getResource("test.txt").getPath();
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
			System.out.println(objectMetadata.toString());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testgetUploadTempCredential()
	{
		try
		{
			Set<String> objectNames = new HashSet<>(Arrays.asList(OBJECTNAME));
			objectStoreageService.getUploadTempCredential(BUCKETNAME, objectNames, 900);
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
			String filePath = TestAliyunObjectStorageServiceImpl.class.getClassLoader().getResource("test.txt").getPath();
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
