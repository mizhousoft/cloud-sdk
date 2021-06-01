package com.mizhousoft.tencent.nlp.validator;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.tencent.RegionEnum;
import com.mizhousoft.tencent.nlp.profile.NLPProfile;

/**
 * NLPProfile校验器
 *
 * @version
 */
public abstract class NLPProfileValidator
{
	public static void validate(NLPProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("NLPProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("NLP access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("NLP secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("NLP region is null.");
		}

		RegionEnum region = RegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("NLP region does not supported, region is " + profile.getRegion() + '.');
		}

		if (StringUtils.isBlank(profile.getEndpoint()))
		{
			throw new CloudSDKException("NLP endpoint is null.");
		}
	}
}
