package com.mizhousoft.cloudsdk.tencent.core.http;

/**
 * 协议
 *
 * @version
 */
public enum HttpProtocol
{
	/**
	 * Https
	 */
	HTTPS("https://"),

	/**
	 * Http
	 */
	HTTP("http://");

	/**
	 * 语言
	 */
	private final String protocol;

	/**
	 * 构造函数
	 *
	 * @param protocol
	 */
	HttpProtocol(String protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * Returns the string representation of the protocol.
	 *
	 */
	@Override
	public String toString()
	{
		return this.protocol;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getValue()
	{
		return this.protocol;
	}
}