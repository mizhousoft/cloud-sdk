package com.mizhousoft.boot.captcha.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 验证码配置
 *
 * @version
 */
@Component
@ConfigurationProperties(prefix = "cloud.captcha")
public class CaptchaProperties
{
	/**
	 * 供应商，如 tencent、aliyun、huawei
	 */
	private volatile String vendor;

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
	 * 验证码应用ID
	 */
	private Long captchaAppId;

	/**
	 * 验证码应用密钥
	 */
	private volatile String captchaAppSecret;

	/**
	 * 获取vendor
	 * 
	 * @return
	 */
	public String getVendor()
	{
		return vendor;
	}

	/**
	 * 设置vendor
	 * 
	 * @param vendor
	 */
	public void setVendor(String vendor)
	{
		this.vendor = vendor;
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
	 * 获取captchaAppId
	 * 
	 * @return
	 */
	public Long getCaptchaAppId()
	{
		return captchaAppId;
	}

	/**
	 * 设置captchaAppId
	 * 
	 * @param captchaAppId
	 */
	public void setCaptchaAppId(Long captchaAppId)
	{
		this.captchaAppId = captchaAppId;
	}

	/**
	 * 获取captchaAppSecret
	 * 
	 * @return
	 */
	public String getCaptchaAppSecret()
	{
		return captchaAppSecret;
	}

	/**
	 * 设置captchaAppSecret
	 * 
	 * @param captchaAppSecret
	 */
	public void setCaptchaAppSecret(String captchaAppSecret)
	{
		this.captchaAppSecret = captchaAppSecret;
	}
}
