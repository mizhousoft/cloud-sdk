package com.mizhousoft.aliyun.oss;

import org.apache.commons.lang3.StringUtils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.Protocol;
import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 构建器
 *
 */
public abstract class AliyunOSSClientBuilder
{
	public static OSS build(AliyunOSSProfile profile) throws CloudSDKException
	{
		validate(profile);

		String endpoint = profile.getEndpoint();

		String accessKeyId = profile.getAccessKey();
		String accessKeySecret = profile.getSecretKey();

		ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
		conf.setProtocol(Protocol.HTTPS);

		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, conf);

		return ossClient;
	}

	private static void validate(AliyunOSSProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("Profile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("Access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("Secret key is null.");
		}

		if (StringUtils.isBlank(profile.getEndpoint()))
		{
			throw new CloudSDKException("Endpoint is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("Region is null.");
		}
	}
}
