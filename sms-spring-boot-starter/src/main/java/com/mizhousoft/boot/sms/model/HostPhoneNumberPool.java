package com.mizhousoft.boot.sms.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 主机池
 *
 * @version
 */
public class HostPhoneNumberPool
{
	// IP地址
	private final String host;

	// 手机号
	private List<String> phoneNumbers;

	/**
	 * 构造函数
	 *
	 * @param host
	 */
	public HostPhoneNumberPool(String host, String phoneNumber)
	{
		super();
		this.host = host;
		this.phoneNumbers = new ArrayList<>(10);
		this.phoneNumbers.add(phoneNumber);
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
	 * 获取phoneNumbers
	 * 
	 * @return
	 */
	public List<String> getPhoneNumbers()
	{
		return phoneNumbers;
	}
}
