package com.mizhousoft.tencent.nlp.impl;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.nlp.RecognitionResult;
import com.mizhousoft.cloudsdk.nlp.SensitiveWordsRecognitionService;
import com.mizhousoft.tencent.nlp.profile.NLPProfile;

/**
 * SensitiveWordsRecognitionServiceImpl Test
 *
 * @version
 */
public class TestSensitiveWordsRecognitionServiceImpl
{
	private static final String ACCESSKEY = "";

	private static final String SECRETKEY = "";

	private static final String REGION = "ap-guangzhou";

	private static final String ENDPOINT = "nlp.tencentcloudapi.com";

	private SensitiveWordsRecognitionService sensitiveWordsRecognitionService;

	@BeforeEach
	public void before() throws CloudSDKException
	{
		NLPProfile profile = new NLPProfile();
		profile.setAccessKey(ACCESSKEY);
		profile.setSecretKey(SECRETKEY);
		profile.setRegion(REGION);
		profile.setEndpoint(ENDPOINT);

		SensitiveWordsRecognitionServiceImpl service = new SensitiveWordsRecognitionServiceImpl();
		service.init(profile);

		this.sensitiveWordsRecognitionService = service;
	}

	@Test
	public void testRecognize()
	{
		String text = "我的敏感词包二奶仓井空";
		try
		{
			RecognitionResult result = this.sensitiveWordsRecognitionService.recognize(text);

			Assertions.assertTrue(result.isContainSensitiveWord());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}

	@Test
	public void testRecognizes()
	{
		String text1 = "ddddddddddddddddddd";
		String text2 = "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd我的敏感词包二奶仓井空";
		text1 = StringUtils.leftPad(text1, 1950, "0");

		try
		{
			RecognitionResult result = this.sensitiveWordsRecognitionService.recognize(text1, text2);

			Assertions.assertTrue(result.isContainSensitiveWord());
		}
		catch (CloudSDKException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
}
