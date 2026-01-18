package com.mizhousoft.cloudsdk;

import com.mizhousoft.commons.data.NestedException;

/**
 * 异常
 *
 * @version
 */
public class CloudSDKNewException extends NestedException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 150630103406481633L;

	/**
	 * 状态码
	 */
	private int httpStatusCode;

	/**
	 * 请求ID
	 */
	private String requestId;

	/**
	 * 错误码
	 */
	private String requestErrorCode;

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param message
	 * @param throwable
	 */
	public CloudSDKNewException(String errorCode, String message, Throwable throwable)
	{
		super(errorCode, message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param message
	 */
	public CloudSDKNewException(String errorCode, String message)
	{
		super(errorCode, message);
	}

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param codeParams
	 * @param message
	 * @param throwable
	 */
	public CloudSDKNewException(String errorCode, String[] codeParams, String message, Throwable throwable)
	{
		super(errorCode, codeParams, message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param codeParams
	 * @param message
	 */
	public CloudSDKNewException(String errorCode, String[] codeParams, String message)
	{
		super(errorCode, codeParams, message);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public CloudSDKNewException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 */
	public CloudSDKNewException(String message)
	{
		super(message);
	}

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

	/**
	 * 获取requestErrorCode
	 * 
	 * @return
	 */
	public String getRequestErrorCode()
	{
		return requestErrorCode;
	}

	/**
	 * 设置requestErrorCode
	 * 
	 * @param requestErrorCode
	 */
	public void setRequestErrorCode(String requestErrorCode)
	{
		this.requestErrorCode = requestErrorCode;
	}
}
