package com.mizhousoft.cloudsdk.nlp.impl;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.nlp.RecognitionResult;
import com.mizhousoft.cloudsdk.nlp.SensitiveWordsRecognitionService;
import com.mizhousoft.tencent.nlp.impl.SensitiveWordsRecognitionServiceImpl;
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

	@Before
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

			Assert.assertTrue(result.isContainSensitiveWord());
		}
		catch (CloudSDKException e)
		{
			Assert.fail(e.getMessage());
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

			Assert.assertTrue(result.isContainSensitiveWord());
		}
		catch (CloudSDKException e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
