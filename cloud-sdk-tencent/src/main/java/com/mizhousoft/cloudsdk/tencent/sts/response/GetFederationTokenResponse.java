package com.mizhousoft.cloudsdk.tencent.sts.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;

/**
 * GetFederationToken Response
 *
 * @version
 */
public class GetFederationTokenResponse extends GeneralResponse
{
	/**
	 * 临时访问凭证
	 */
	@JsonProperty("Credentials")
	private Credentials credentials;

	/**
	 * 临时访问凭证有效的时间，返回 Unix 时间戳，精确到秒
	 */
	@JsonProperty("ExpiredTime")
	private Long expiredTime;

	/**
	 * 临时访问凭证有效的时间，以 iso8601 格式的 UTC 时间表示
	 * 注意：此字段可能返回 null，表示取不到有效值。
	 */
	@JsonProperty("Expiration")
	private String expiration;

	/**
	 * 获取credentials
	 * 
	 * @return
	 */
	public Credentials getCredentials()
	{
		return credentials;
	}

	/**
	 * 设置credentials
	 * 
	 * @param credentials
	 */
	public void setCredentials(Credentials credentials)
	{
		this.credentials = credentials;
	}

	/**
	 * 获取expiredTime
	 * 
	 * @return
	 */
	public Long getExpiredTime()
	{
		return expiredTime;
	}

	/**
	 * 设置expiredTime
	 * 
	 * @param expiredTime
	 */
	public void setExpiredTime(Long expiredTime)
	{
		this.expiredTime = expiredTime;
	}

	/**
	 * 获取expiration
	 * 
	 * @return
	 */
	public String getExpiration()
	{
		return expiration;
	}

	/**
	 * 设置expiration
	 * 
	 * @param expiration
	 */
	public void setExpiration(String expiration)
	{
		this.expiration = expiration;
	}
}
