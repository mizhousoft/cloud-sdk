package com.mizhousoft.cloudsdk.tencent.common;

/**
 * 语言
 *
 * @version
 */
public enum Language
{
	/**
	 * Chinese Simplified language code.
	 * Represents the "zh-CN" language code.
	 */
	ZH_CN("zh-CN"),

	/**
	 * English language code.
	 * Represents the "en-US" language code.
	 */
	EN_US("en-US");

	/**
	 * 语言
	 */
	private final String lang;

	/**
	 * 构造函数
	 *
	 * @param lang
	 */
	Language(String lang)
	{
		this.lang = lang;
	}

	/**
	 * Returns the string representation of the language code.
	 *
	 * @return The language code as a string (e.g., "zh-CN" or "en-US").
	 */
	@Override
	public String toString()
	{
		return this.lang;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getValue()
	{
		return this.lang;
	}
}