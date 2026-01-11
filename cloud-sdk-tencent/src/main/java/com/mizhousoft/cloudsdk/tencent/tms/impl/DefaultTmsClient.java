package com.mizhousoft.cloudsdk.tencent.tms.impl;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.tencent.common.APIResponse;
import com.mizhousoft.cloudsdk.tencent.common.AbstractClient;
import com.mizhousoft.cloudsdk.tencent.common.ClientProfile;
import com.mizhousoft.cloudsdk.tencent.common.RegionEnum;
import com.mizhousoft.cloudsdk.tencent.core.Credential;
import com.mizhousoft.cloudsdk.tencent.core.http.MediaType;
import com.mizhousoft.cloudsdk.tencent.core.impl.DefaultHttpRequest;
import com.mizhousoft.cloudsdk.tencent.tms.TmsClient;
import com.mizhousoft.cloudsdk.tencent.tms.request.TextModerationRequest;
import com.mizhousoft.cloudsdk.tencent.tms.response.TextModerationResponse;
import com.mizhousoft.cloudsdk.tms.TextModerationResult;
import com.mizhousoft.commons.lang.CharEncoding;

import kong.unirest.core.HttpMethod;
import tools.jackson.core.type.TypeReference;

/**
 * TmsClient
 *
 * @version
 */
public class DefaultTmsClient extends AbstractClient implements TmsClient
{
	private static final String ENDPOINT = "tms.tencentcloudapi.com";

	private static final String API_VERSION = "2020-12-29";

	private static final int MAX_TEXT_LENGTH = 5000;

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 */
	public DefaultTmsClient(RegionEnum region, Credential credential)
	{
		this(ENDPOINT, API_VERSION, region, credential, new ClientProfile());
	}

	/**
	 * 构造函数
	 *
	 * @param region
	 * @param credential
	 * @param profile
	 */
	public DefaultTmsClient(RegionEnum region, Credential credential, ClientProfile profile)
	{
		this(ENDPOINT, API_VERSION, region, credential, profile);
	}

	/**
	 * 构造函数
	 *
	 * @param endpoint
	 * @param apiVersion
	 * @param region
	 * @param credential
	 * @param profile
	 */
	public DefaultTmsClient(String endpoint, String apiVersion, RegionEnum region, Credential credential, ClientProfile profile)
	{
		super(endpoint, apiVersion, region, credential, profile);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TextModerationResult execute(String... texts) throws CloudSDKNewException
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

	/**
	 * 识别单个
	 * 
	 * @param text
	 * @return
	 * @throws CloudSDKNewException
	 */
	public TextModerationResult recognizeOne(String text) throws CloudSDKNewException
	{
		TextModerationResult result = new TextModerationResult();
		if (StringUtils.isBlank(text))
		{
			return result;
		}

		TextModerationRequest request = new TextModerationRequest();

		String content = Base64.encodeBase64String(text.getBytes(CharEncoding.UTF8));
		request.setContent(content);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("TextModeration")
		        .protocol(profile.getProtocol())
		        .endpoint(endpoint)
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .bodyAsString(request)
		        .build();

		Map<String, String> headerMap = buildSignHeader(httpRequest, profile, credential);

		TextModerationResponse response = executeRequest(httpRequest, headerMap, new TypeReference<APIResponse<TextModerationResponse>>()
		{
		});

		String[] words = response.getKeywords();
		if (!ArrayUtils.isEmpty(words))
		{
			result.setKeywords(Arrays.asList(words));
		}

		result.setLabel(response.getLabel());
		result.setSuggestion(response.getSuggestion());

		return result;
	}
}
