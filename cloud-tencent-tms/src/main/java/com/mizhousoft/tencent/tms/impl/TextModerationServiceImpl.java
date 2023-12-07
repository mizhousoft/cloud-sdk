package com.mizhousoft.tencent.tms.impl;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tms.TextModerationResult;
import com.mizhousoft.cloudsdk.tms.TextModerationService;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.tencent.tms.profile.TMSProfile;
import com.mizhousoft.tencent.tms.validator.TMSProfileValidator;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tms.v20201229.TmsClient;
import com.tencentcloudapi.tms.v20201229.models.TextModerationRequest;
import com.tencentcloudapi.tms.v20201229.models.TextModerationResponse;

/**
 * 文本内容服务
 *
 * @version
 */
public class TextModerationServiceImpl implements TextModerationService
{
	private static final Logger LOG = LoggerFactory.getLogger(TextModerationServiceImpl.class);

	private static final int MAX_TEXT_LENGTH = 5000;

	private TmsClient tmsClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TextModerationResult execute(String... texts) throws CloudSDKException
	{
		TextModerationResult result = new TextModerationResult();
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
				if (result.isContainKeyword())
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

	public TextModerationResult recognizeOne(String text) throws CloudSDKException
	{
		TextModerationResult result = new TextModerationResult();
		if (StringUtils.isBlank(text))
		{
			return result;
		}

		try
		{
			TextModerationRequest request = new TextModerationRequest();

			String content = Base64.encodeBase64String(text.getBytes(CharEncoding.UTF8));
			request.setContent(content);

			TextModerationResponse resp = tmsClient.TextModeration(request);

			String[] words = resp.getKeywords();
			if (!ArrayUtils.isEmpty(words))
			{
				result.setKeywords(Arrays.asList(words));
			}

			result.setLabel(resp.getLabel());
			result.setSuggestion(resp.getSuggestion());
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}

		return result;
	}

	public void init(TMSProfile profile) throws CloudSDKException
	{
		TMSProfileValidator.validate(profile);

		Credential cred = new Credential(profile.getAccessKey(), profile.getSecretKey());

		// 实例化一个http选项，可选的，没有特殊需求可以跳过
		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(profile.getEndpoint());
		// 实例化一个client选项，可选的，没有特殊需求可以跳过
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);
		// 实例化要请求产品的client对象,clientProfile是可选的
		TmsClient client = new TmsClient(cred, profile.getRegion(), clientProfile);

		this.tmsClient = client;

		LOG.info("Init TMS client successfully.");
	}
}
