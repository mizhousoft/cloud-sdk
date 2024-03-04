package com.mizhousoft.aliyun.boot.oss.configuration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mizhousoft.aliyun.boot.oss.properties.OSSProfileListProperties;
import com.mizhousoft.aliyun.boot.oss.properties.OSSProfileProperties;
import com.mizhousoft.aliyun.oss.AliyunOSSClient;
import com.mizhousoft.aliyun.oss.AliyunOSSProfile;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.oss.OSSClient;

/**
 * AliyunOSSConfiguration
 *
 * @version
 */
@Configuration
public class AliyunOSSConfiguration
{
	@Autowired
	private OSSProfileListProperties listProperties;

	@Bean(destroyMethod = "destroy")
	public OSSClient getOSSClient() throws CloudSDKException
	{
		AliyunOSSClient ossClient = new AliyunOSSClient();

		List<OSSProfileProperties> list = listProperties.getList();
		if (null != list)
		{
			for (OSSProfileProperties item : list)
			{
				if (StringUtils.isBlank(item.getIdentifier()))
				{
					throw new CloudSDKException("OSS identifier is null.");
				}

				AliyunOSSProfile ossProfile = new AliyunOSSProfile();
				ossProfile.setBucketName(item.getBucketName());
				ossProfile.setAccessKey(item.getAccessKey());
				ossProfile.setSecretKey(item.getSecretKey());
				ossProfile.setRegion(item.getRegion());
				ossProfile.setEndpoint(item.getOssEndpoint());
				ossProfile.setStsEndpoint(item.getStsEndpoint());
				ossProfile.setRoleArn(item.getRoleArn());
				ossProfile.setRoleSessionName(item.getRoleSessionName());

				if (null != item.getCdnEndpoint())
				{
					CDNProfile cdnProfile = new CDNProfile();
					cdnProfile.setEndpoint(item.getCdnEndpoint());
					cdnProfile.setAuthzEnable(null != item.getSecretKey());
					cdnProfile.setAuthzMode(item.getCdnAuthzMode());
					cdnProfile.setSecretKey(item.getCdnSecretKey());

					ossClient.addBucketService(item.getIdentifier(), ossProfile, cdnProfile);
				}
				else
				{
					ossClient.addBucketService(item.getIdentifier(), ossProfile);
				}
			}
		}

		return ossClient;
	}
}
