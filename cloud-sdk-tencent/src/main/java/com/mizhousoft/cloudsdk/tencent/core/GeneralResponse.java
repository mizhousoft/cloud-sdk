package com.mizhousoft.cloudsdk.tencent.core;

/**
 * 响应
 *
 * @version
 */
public class GeneralResponse
{
	/**
	 * 状态码
	 */
	protected int httpStatusCode;

	/**
	 * 请求ID
	 */
	protected String requestId;

	/**
	 * 获取httpStatusCode
	 * 
	 * @return
	 */
	public int getHttpStatusCode()
	{
		return httpStatusCode;
	}

	/**
	 * 设置httpStatusCode
	 * 
	 * @param httpStatusCode
	 */
	public void setHttpStatusCode(int httpStatusCode)
	{
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * 获取requestId
	 * 
	 * @return
	 */
	public String getRequestId()
	{
		return requestId;
	}

	/**
	 * 设置requestId
	 * 
	 * @param requestId
	 */
	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}
}
