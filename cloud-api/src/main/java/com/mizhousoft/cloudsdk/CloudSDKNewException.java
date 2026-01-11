package com.mizhousoft.cloudsdk;

/**
 * 异常
 *
 * @version
 */
public class CloudSDKNewException extends Exception
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
	private String errorCode;

	/**
	 * 构造函数
	 *
	 * @param httpStatusCode
	 * @param requestId
	 * @param errorCode
	 * @param message
	 */
	public CloudSDKNewException(int httpStatusCode, String requestId, String errorCode, String message)
	{
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.requestId = requestId;
		this.errorCode = errorCode;
	}

	/**
	 * 构造函数
	 *
	 * @param httpStatusCode
	 * @param requestId
	 * @param errorCode
	 */
	public CloudSDKNewException(int httpStatusCode, String requestId, String errorCode)
	{
		super();
		this.httpStatusCode = httpStatusCode;
		this.requestId = requestId;
		this.errorCode = errorCode;
	}

	/**
	 * 构造函数
	 *
	 * @param httpStatusCode
	 * @param message
	 */
	public CloudSDKNewException(int httpStatusCode, String message)
	{
		super(message);
		this.httpStatusCode = httpStatusCode;
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
	 * 获取errorCode
	 * 
	 * @return
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * 设置errorCode
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
}
