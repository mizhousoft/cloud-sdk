package com.mizhousoft.tencent.vod.impl;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.vod.MediaMeta;
import com.mizhousoft.cloudsdk.vod.VODService;
import com.mizhousoft.tencent.vod.profile.VodProfile;

/**
 * QCloudVODServiceImpl Test
 *
 * @version
 */
public class TestQCloudVODServiceImpl
{
	private static final String ACCESSKEY = "";

	private static final String SECRETKEY = "";

	private static final String REGION = "ap-guangzhou";

	private static final String ENDPOINT = "vod.ap-guangzhou.tencentcloudapi.com";

	private VODService vodService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		VodProfile profile = new VodProfile();
		profile.setAccessKey(ACCESSKEY);
		profile.setSecretKey(SECRETKEY);
		profile.setRegion(REGION);
		profile.setEndpoint(ENDPOINT);

		QCloudVODServiceImpl service = new QCloudVODServiceImpl();
		service.init(profile);

		this.vodService = service;
	}

	@Test
	public void uploadVideo()
	{
		String filePath = TestQCloudVODServiceImpl.class.getClassLoader().getResource("test.mp4").getPath();
		File mediaFile = new File(filePath);
		filePath = TestQCloudVODServiceImpl.class.getClassLoader().getResource("test-p.jpg").getPath();
		File coverFile = new File(filePath);

		try
		{
			vodService.uploadVideo(mediaFile, coverFile);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void deleteVideo()
	{
		try
		{
			vodService.deleteVideo("5285890786997444054");
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void getVideoMeta()
	{
		try
		{
			MediaMeta mediaMeta = vodService.getVideoMeta("5285890786997530566");

			Assertions.assertNotNull(mediaMeta);
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
}
