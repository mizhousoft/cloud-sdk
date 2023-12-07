package com.mizhousoft.tencent.oss;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TencentRegionEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;

/**
 * 构建器
 *
 */
public abstract class COSClientBuilder
{
	public static COSClient build(COSProfile cosProfile) throws CloudSDKException
	{
		COSClientBuilder.validate(cosProfile);

		COSCredentials credentials = null;
		if (StringUtils.isBlank(cosProfile.getSessionToken()))
		{
			credentials = new BasicCOSCredentials(cosProfile.getAccessKey(), cosProfile.getSecretKey());
		}
		else
		{
			credentials = new BasicSessionCredentials(cosProfile.getAccessKey(), cosProfile.getSecretKey(), cosProfile.getSessionToken());
		}

		TencentRegionEnum region = TencentRegionEnum.get(cosProfile.getRegion());
		ClientConfig clientConfig = new ClientConfig(new Region(region.getValue()));
		clientConfig.setHttpProtocol(HttpProtocol.https);

		COSClient cosClient = new COSClient(credentials, clientConfig);

		return cosClient;
	}

	public static void validate(COSProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("COSProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("Cos access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("Cos secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("Cos region is null.");
		}

		TencentRegionEnum region = TencentRegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("Cos region does not supported, region is " + profile.getRegion() + '.');
		}
	}
}
