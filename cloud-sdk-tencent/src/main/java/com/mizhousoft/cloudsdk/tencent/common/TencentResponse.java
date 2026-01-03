package com.mizhousoft.cloudsdk.tencent.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;

/**
 * 响应
 *
 * @version
 */
public class TencentResponse extends GeneralResponse
{
	/**
	 * 错误
	 */
	@JsonProperty("Error")
	protected APIError error;

	/**
	 * {@inheritDoc}
	 */
	@JsonProperty("RequestId")
	@Override
	public void setRequestId(String requestId)
	{
		super.setRequestId(requestId);
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
