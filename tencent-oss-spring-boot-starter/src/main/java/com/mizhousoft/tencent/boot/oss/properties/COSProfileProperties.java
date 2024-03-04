package com.mizhousoft.tencent.boot.oss.properties;

import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @version
 */
@Component
public class COSProfileProperties
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
	 * CDN Endpoint
	 */
	private volatile String cdnEndpoint;

	/**
	 * CDN鉴权模式
	 */
	private volatile String cdnAuthzMode;

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
	 * 获取cdnAuthzMode
	 * 
	 * @return
	 */
	public String getCdnAuthzMode()
	{
		return cdnAuthzMode;
	}

	/**
	 * 设置cdnAuthzMode
	 * 
	 * @param cdnAuthzMode
	 */
	public void setCdnAuthzMode(String cdnAuthzMode)
	{
		this.cdnAuthzMode = cdnAuthzMode;
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
