package com.mizhousoft.cloudsdk.tencent.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 响应
 *
 * @version
 */
public class APIResponse<T>
{
	/**
	 * 发送数据统计响应包体。
	 */
	@JsonProperty("Response")
	private T response;

	/**
	 * 错误
	 */
	@JsonProperty("Error")
	private APIError error;

	/**
	 * 获取response
	 * 
	 * @return
	 */
	public T getResponse()
	{
		return response;
	}

	/**
	 * 设置response
	 * 
	 * @param response
	 */
	public void setResponse(T response)
	{
		this.response = response;
	}

	/**
	 * 获取error
	 * 
	 * @return
	 */
	public APIError getError()
	{
		return error;
	}

	/**
	 * 设置error
	 * 
	 * @param error
	 */
	public void setError(APIError error)
	{
		this.error = error;
	}
}
