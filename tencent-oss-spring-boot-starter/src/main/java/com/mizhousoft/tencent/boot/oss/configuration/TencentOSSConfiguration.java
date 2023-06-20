package com.mizhousoft.tencent.boot.oss.configuration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.cdn.CDNProfile;
import com.mizhousoft.cloudsdk.oss.OSSClient;
import com.mizhousoft.tencent.boot.oss.properties.COSProfileListProperties;
import com.mizhousoft.tencent.boot.oss.properties.COSProfileProperties;
import com.mizhousoft.tencent.oss.COSProfile;
import com.mizhousoft.tencent.oss.TencentOSSClient;

/**
 * TencentOSSConfiguration
 *
 * @version
 */
@Configuration
public class TencentOSSConfiguration
{
	@Autowired
	private COSProfileListProperties listProperties;

	@Bean(destroyMethod = "destroy")
	public OSSClient getOSSClient() throws CloudSDKException
	{
		TencentOSSClient ossClient = new TencentOSSClient();

		List<COSProfileProperties> list = listProperties.getList();
		if (null != list)
		{
			for (COSProfileProperties item : list)
			{
				if (StringUtils.isBlank(item.getIdentifier()))
				{
					throw new CloudSDKException("OSS identifier is null.");
				}

				COSProfile cosProfile = new COSProfile();
				cosProfile.setBucketName(item.getBucketName());
				cosProfile.setAccessKey(item.getAccessKey());
				cosProfile.setSecretKey(item.getSecretKey());
				cosProfile.setRegion(item.getRegion());

				if (null != item.getCdnEndpoint())
				{
					CDNProfile cdnProfile = new CDNProfile();
					cdnProfile.setEndpoint(item.getCdnEndpoint());
					cdnProfile.setSecretKey(item.getCdnSecretKey());
					cdnProfile.setUrlAuthzEnable(null != item.getSecretKey());

					ossClient.addBucketService(item.getIdentifier(), cosProfile, cdnProfile);
				}
				else
				{
					ossClient.addBucketService(item.getIdentifier(), cosProfile);
				}
			}
		}

		return ossClient;
	}
}
