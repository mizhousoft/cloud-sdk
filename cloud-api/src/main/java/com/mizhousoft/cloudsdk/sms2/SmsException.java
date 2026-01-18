package com.mizhousoft.cloudsdk.sms2;

import com.mizhousoft.cloudsdk.CloudSDKNewException;

/**
 * 异常
 *
 * @version
 */
public class SmsException extends CloudSDKNewException
{
	private static final long serialVersionUID = -5000706925600173532L;

	/**
	 * 失败的手机号
	 */
	private String[] failedPhoneNumbers;

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param message
	 * @param throwable
	 */
	public SmsException(String errorCode, String message, Throwable throwable)
	{
		super(errorCode, message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param errorCode
	 * @param message
	 */
	public SmsException(String errorCode, String message)
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
	public SmsException(String errorCode, String[] codeParams, String message, Throwable throwable)
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
	public SmsException(String errorCode, String[] codeParams, String message)
	{
		super(errorCode, codeParams, message);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public SmsException(String message, Throwable throwable)
	{
		super(message, throwable);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 */
	public SmsException(String message)
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
