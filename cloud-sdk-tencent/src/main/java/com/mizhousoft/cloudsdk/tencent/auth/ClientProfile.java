package com.mizhousoft.cloudsdk.tencent.auth;

import com.mizhousoft.cloudsdk.tencent.core.http.HttpProtocol;

/**
 * 配置
 *
 * @version
 */
public class ClientProfile
{
	/**
	 * The protocol used for the request. Currently, only HTTPS is valid.
	 */
	private HttpProtocol protocol;

	/**
	 * valid choices: zh-CN, en-US
	 */
	private Language language;

	/**
	 * 构造函数
	 *
	 */
	public ClientProfile()
	{
		this.protocol = HttpProtocol.HTTPS;
	}

	/**
	 * 获取protocol
	 * 
	 * @return
	 */
	public HttpProtocol getProtocol()
	{
		return protocol;
	}

	/**
	 * 设置protocol
	 * 
	 * @param protocol
	 */
	public void setProtocol(HttpProtocol protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * 获取language
	 * 
	 * @return
	 */
	public Language getLanguage()
	{
		return language;
	}

	/**
	 * 设置language
	 * 
	 * @param language
	 */
	public void setLanguage(Language language)
	{
		this.language = language;
	}
}
