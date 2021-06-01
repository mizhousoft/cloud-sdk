package com.mizhousoft.tencent.nlp.impl;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.nlp.RecognitionResult;
import com.mizhousoft.cloudsdk.nlp.SensitiveWordsRecognitionService;
import com.mizhousoft.tencent.nlp.profile.NLPProfile;
import com.mizhousoft.tencent.nlp.validator.NLPProfileValidator;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.nlp.v20190408.models.SensitiveWordsRecognitionRequest;
import com.tencentcloudapi.nlp.v20190408.models.SensitiveWordsRecognitionResponse;

/**
 * 敏感词识别服务
 *
 * @version
 */
public class SensitiveWordsRecognitionServiceImpl implements SensitiveWordsRecognitionService
{
	private static final Logger LOG = LoggerFactory.getLogger(SensitiveWordsRecognitionServiceImpl.class);

	private static final int MAX_TEXT_LENGTH = 2000;

	private NlpClient nlpClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RecognitionResult recognize(String... texts) throws CloudSDKException
	{
		RecognitionResult result = new RecognitionResult();
		if (ArrayUtils.isEmpty(texts))
		{
			return result;
		}

		StringBuilder buffer = new StringBuilder(100);
		for (String text : texts)
		{
			if (!StringUtils.isBlank(text))
			{
				buffer.append(text.trim());
			}
		}

		String value = buffer.toString();
		while (true)
		{
			if (value.length() > MAX_TEXT_LENGTH)
			{
				String data = value.substring(0, MAX_TEXT_LENGTH);
				result = recognizeOne(data);
				if (result.isContainSensitiveWord())
				{
					break;
				}
				else
				{
					value = value.substring(MAX_TEXT_LENGTH);
				}
			}
			else
			{
				result = recognizeOne(value);
				break;
			}
		}

		return result;
	}

	public RecognitionResult recognizeOne(String text) throws CloudSDKException
	{
		RecognitionResult result = new RecognitionResult();
		if (StringUtils.isBlank(text))
		{
			return result;
		}

		try
		{
			SensitiveWordsRecognitionRequest request = new SensitiveWordsRecognitionRequest();
			request.setText(text);

			SensitiveWordsRecognitionResponse resp = nlpClient.SensitiveWordsRecognition(request);
			String[] words = resp.getSensitiveWords();

			if (!ArrayUtils.isEmpty(words))
			{
				result.setSensitiveWords(Arrays.asList(words));
			}
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}

		return result;
	}

	public void init(NLPProfile profile) throws CloudSDKException
	{
		NLPProfileValidator.validate(profile);

		Credential cred = new Credential(profile.getAccessKey(), profile.getSecretKey());

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(profile.getEndpoint());

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		NlpClient client = new NlpClient(cred, profile.getRegion(), clientProfile);

		this.nlpClient = client;

		LOG.info("Init nlp client successfully.");
	}
}
