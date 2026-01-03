package com.mizhousoft.cloudsdk.tencent.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 错误
 *
 * @version
 */
public class TencentError
{
	/**
	 * 错误码
	 */
	@JsonProperty("Code")
	private String code;

	/**
	 * 错误信息
	 */
	@JsonProperty("Message")
	private String message;

	/**
	 * 获取Code
	 * 
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 设置Code
	 * 
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * 获取message
	 * 
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * 设置message
	 * 
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}
}
