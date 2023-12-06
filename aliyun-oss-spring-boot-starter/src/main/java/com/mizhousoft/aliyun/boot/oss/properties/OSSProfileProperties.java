package com.mizhousoft.aliyun.boot.oss.properties;

import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @version
 */
@Component
public class OSSProfileProperties
{
	/**
	 * identifier
	 */
	private volatile String identifier;

	/**
	 * 桶名
	 */
	private volatile String bucketName;

	/**
	 * 访问KEY
	 */
	private volatile String accessKey;

	/**
	 * 访问密钥
	 */
	private volatile String secretKey;

	/**
	 * 区域
	 */
	private volatile String region;

	/**
	 * endpoint
	 */
	private String ossEndpoint;

	/**
	 * STS Endpoint
	 */
	private String stsEndpoint;

	/**
	 * 角色ARN
	 */
	private String roleArn;

	/**
	 * 用来标识临时访问凭证的名称，建议使用不同的应用程序用户来区分
	 */
	private String roleSessionName;

	/**
	 * CDN Endpoint
	 */
	private volatile String cdnEndpoint;

	/**
	 * CDN密钥
	 */
	private volatile String cdnSecretKey;

	/**
	 * 获取identifier
	 * 
	 * @return
	 */
	public String getIdentifier()
	{
		return identifier;
	}

	/**
	 * 设置identifier
	 * 
	 * @param identifier
	 */
	public void setIdentifier(String identifier)
	{
		this.identifier = identifier;
	}

	/**
	 * 获取bucketName
	 * 
	 * @return
	 */
	public String getBucketName()
	{
		return bucketName;
	}

	/**
	 * 设置bucketName
	 * 
	 * @param bucketName
	 */
	public void setBucketName(String bucketName)
	{
		this.bucketName = bucketName;
	}

	/**
	 * 获取accessKey
	 * 
	 * @return
	 */
	public String getAccessKey()
	{
		return accessKey;
	}

	/**
	 * 设置accessKey
	 * 
	 * @param accessKey
	 */
	public void setAccessKey(String accessKey)
	{
		this.accessKey = accessKey;
	}

	/**
	 * 获取secretKey
	 * 
	 * @return
	 */
	public String getSecretKey()
	{
		return secretKey;
	}

	/**
	 * 设置secretKey
	 * 
	 * @param secretKey
	 */
	public void setSecretKey(String secretKey)
	{
		this.secretKey = secretKey;
	}

	/**
	 * 获取region
	 * 
	 * @return
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * 设置region
	 * 
	 * @param region
	 */
	public void setRegion(String region)
	{
		this.region = region;
	}

	/**
	 * 获取ossEndpoint
	 * 
	 * @return
	 */
	public String getOssEndpoint()
	{
		return ossEndpoint;
	}

	/**
	 * 设置ossEndpoint
	 * 
	 * @param ossEndpoint
	 */
	public void setOssEndpoint(String ossEndpoint)
	{
		this.ossEndpoint = ossEndpoint;
	}

	/**
	 * 获取stsEndpoint
	 * 
	 * @return
	 */
	public String getStsEndpoint()
	{
		return stsEndpoint;
	}

	/**
	 * 设置stsEndpoint
	 * 
	 * @param stsEndpoint
	 */
	public void setStsEndpoint(String stsEndpoint)
	{
		this.stsEndpoint = stsEndpoint;
	}

	/**
	 * 获取roleArn
	 * 
	 * @return
	 */
	public String getRoleArn()
	{
		return roleArn;
	}

	/**
	 * 设置roleArn
	 * 
	 * @param roleArn
	 */
	public void setRoleArn(String roleArn)
	{
		this.roleArn = roleArn;
	}

	/**
	 * 获取roleSessionName
	 * 
	 * @return
	 */
	public String getRoleSessionName()
	{
		return roleSessionName;
	}

	/**
	 * 设置roleSessionName
	 * 
	 * @param roleSessionName
	 */
	public void setRoleSessionName(String roleSessionName)
	{
		this.roleSessionName = roleSessionName;
	}

	/**
	 * 获取cdnEndpoint
	 * 
	 * @return
	 */
	public String getCdnEndpoint()
	{
		return cdnEndpoint;
	}

	/**
	 * 设置cdnEndpoint
	 * 
	 * @param cdnEndpoint
	 */
	public void setCdnEndpoint(String cdnEndpoint)
	{
		this.cdnEndpoint = cdnEndpoint;
	}

	/**
	 * 获取cdnSecretKey
	 * 
	 * @return
	 */
	public String getCdnSecretKey()
	{
		return cdnSecretKey;
	}

	/**
	 * 设置cdnSecretKey
	 * 
	 * @param cdnSecretKey
	 */
	public void setCdnSecretKey(String cdnSecretKey)
	{
		this.cdnSecretKey = cdnSecretKey;
	}
}
