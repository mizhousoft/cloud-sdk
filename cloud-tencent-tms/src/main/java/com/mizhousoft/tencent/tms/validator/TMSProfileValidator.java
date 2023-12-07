package com.mizhousoft.tencent.tms.validator;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TencentRegionEnum;
import com.mizhousoft.tencent.tms.profile.TMSProfile;

/**
 * TMSProfile校验器
 *
 * @version
 */
public abstract class TMSProfileValidator
{
	public static void validate(TMSProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("TMSProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("TMS access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("TMS secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("TMS region is null.");
		}

		TencentRegionEnum region = TencentRegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("TMS region does not supported, region is " + profile.getRegion() + '.');
		}

		if (StringUtils.isBlank(profile.getEndpoint()))
		{
			throw new CloudSDKException("TMS endpoint is null.");
		}
	}
}
