package com.mizhousoft.tencent.tms.impl;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tms.TextModerationResult;
import com.mizhousoft.cloudsdk.tms.TextModerationService;
import com.mizhousoft.tencent.tms.profile.TMSProfile;

/**
 * TextModerationServiceImpl Test
 *
 * @version
 */
public class TextModerationServiceImplTest
{
	private static final String ACCESSKEY = "";

	private static final String SECRETKEY = "";

	private static final String REGION = "ap-guangzhou";

	private static final String ENDPOINT = "tms.tencentcloudapi.com";

	private TextModerationService textModerationService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		TMSProfile profile = new TMSProfile();
		profile.setAccessKey(ACCESSKEY);
		profile.setSecretKey(SECRETKEY);
		profile.setRegion(REGION);
		profile.setEndpoint(ENDPOINT);

		TextModerationServiceImpl service = new TextModerationServiceImpl();
		service.init(profile);

		this.textModerationService = service;
	}

	@Test
	public void execute()
	{
		String text = "我的敏感词包二奶仓井空";
		try
		{
			TextModerationResult result = this.textModerationService.execute(text);

			Assertions.assertTrue(result.isContainKeyword());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void execute2()
	{
		String text1 = "ddddddddddddddddddd";
		String text2 = "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd我的敏感词包二奶仓井空";
		text1 = StringUtils.leftPad(text1, 1950, "0");

		try
		{
			TextModerationResult result = this.textModerationService.execute(text1, text2);

			Assertions.assertTrue(result.isContainKeyword());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void execute3()
	{
		String text1 = "ddddddddddddddddddd";
		String text2 = "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
		text1 = StringUtils.leftPad(text1, 1950, "0");

		try
		{
			TextModerationResult result = this.textModerationService.execute(text1, text2);

			Assertions.assertFalse(result.isContainKeyword());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
}
