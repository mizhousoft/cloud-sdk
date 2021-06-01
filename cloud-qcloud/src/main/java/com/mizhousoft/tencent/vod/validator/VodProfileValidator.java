package com.mizhousoft.tencent.vod.validator;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.tencent.RegionEnum;
import com.mizhousoft.tencent.vod.profile.VodProfile;

/**
 * VodProfile校验器
 *
 * @version
 */
public abstract class VodProfileValidator
{
	public static void validate(VodProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("VodProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("Vod access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("Vod secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("Vod region is null.");
		}

		RegionEnum region = RegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("Vod region does not supported, region is " + profile.getRegion() + '.');
		}

		if (StringUtils.isBlank(profile.getEndpoint()))
		{
			throw new CloudSDKException("Vod endpoint is null.");
		}
	}
}
