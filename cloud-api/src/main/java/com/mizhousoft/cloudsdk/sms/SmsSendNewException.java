package com.mizhousoft.cloudsdk.sms;

import com.mizhousoft.cloudsdk.CloudSDKNewException;

/**
 * 异常
 *
 * @version
 */
public class SmsSendNewException extends CloudSDKNewException
{
	private static final long serialVersionUID = -5000706925600173532L;

	/**
	 * 失败的手机号
	 */
	private String[] failedPhoneNumbers;

	/**
	 * 构造函数
	 *
	 * @param statusCode
	 * @param requestId
	 * @param errorCode
	 */
	public SmsSendNewException(int statusCode, String requestId, String errorCode)
	{
		super(statusCode, requestId, errorCode);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public SmsSendNewException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 */
	public SmsSendNewException(String message)
	{
		super(message);
	}

	/**
	 * 获取failedPhoneNumbers
	 * 
	 * @return
	 */
	public String[] getFailedPhoneNumbers()
	{
		return failedPhoneNumbers;
	}

	/**
	 * 设置failedPhoneNumbers
	 * 
	 * @param failedPhoneNumbers
	 */
	public void setFailedPhoneNumbers(String[] failedPhoneNumbers)
	{
		this.failedPhoneNumbers = failedPhoneNumbers;
	}
}
