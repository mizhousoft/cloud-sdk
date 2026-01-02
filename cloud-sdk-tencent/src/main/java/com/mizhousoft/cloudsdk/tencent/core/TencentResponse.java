package com.mizhousoft.cloudsdk.tencent.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 响应
 *
 * @version
 */
public class TencentResponse<T>
{
	/**
	 * 发送数据统计响应包体。
	 */
	@JsonProperty("Response")
	private T response;

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
}
