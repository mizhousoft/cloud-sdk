package com.mizhousoft.cloudsdk.huawei.cdn.response;

/**
 * 响应
 *
 * @version
 */
public class CDNResponse
{
	/**
	 * 状态码
	 */
	private int httpStatusCode;

	/**
	 * 
	 */
	private String xRequestId;

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
	 * 获取xRequestId
	 * 
	 * @return
	 */
	public String getxRequestId()
	{
		return xRequestId;
	}

	/**
	 * 设置xRequestId
	 * 
	 * @param xRequestId
	 */
	public void setxRequestId(String xRequestId)
	{
		this.xRequestId = xRequestId;
	}
}
