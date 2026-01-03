package com.mizhousoft.cloudsdk.tencent.sts.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 凭证
 *
 * @version
 */
public class Credentials
{
	/**
	 * token。token长度和绑定的策略有关，最长不超过4096字节。
	 */
	@JsonProperty("Token")
	private transient String token;

	/**
	 * 临时证书密钥ID。最长不超过1024字节。
	 */
	@JsonProperty("TmpSecretId")
	private transient String tmpSecretId;

	/**
	 * 临时证书密钥Key。最长不超过1024字节。
	 */
	@JsonProperty("TmpSecretKey")
	private transient String tmpSecretKey;

	/**
	 * 获取token
	 * 
	 * @return
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * 设置token
	 * 
	 * @param token
	 */
	public void setToken(String token)
	{
		this.token = token;
	}

	/**
	 * 获取tmpSecretId
	 * 
	 * @return
	 */
	public String getTmpSecretId()
	{
		return tmpSecretId;
	}

	/**
	 * 设置tmpSecretId
	 * 
	 * @param tmpSecretId
	 */
	public void setTmpSecretId(String tmpSecretId)
	{
		this.tmpSecretId = tmpSecretId;
	}

	/**
	 * 获取tmpSecretKey
	 * 
	 * @return
	 */
	public String getTmpSecretKey()
	{
		return tmpSecretKey;
	}

	/**
	 * 设置tmpSecretKey
	 * 
	 * @param tmpSecretKey
	 */
	public void setTmpSecretKey(String tmpSecretKey)
	{
		this.tmpSecretKey = tmpSecretKey;
	}
}
