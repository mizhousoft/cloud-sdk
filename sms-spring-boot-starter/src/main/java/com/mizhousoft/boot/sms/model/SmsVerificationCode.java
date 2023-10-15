package com.mizhousoft.boot.sms.model;

/**
 * 短信验证码
 *
 * @version
 */
public class SmsVerificationCode
{
	// 主机
	private final String host;

	// 验证码
	private final String verificationCode;

	/**
	 * 构造函数
	 *
	 * @param host
	 * @param verificationCode
	 */
	public SmsVerificationCode(String host, String verificationCode)
	{
		super();
		this.host = host;
		this.verificationCode = verificationCode;
	}

	/**
	 * 获取host
	 * 
	 * @return
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * 获取verificationCode
	 * 
	 * @return
	 */
	public String getVerificationCode()
	{
		return verificationCode;
	}
}
