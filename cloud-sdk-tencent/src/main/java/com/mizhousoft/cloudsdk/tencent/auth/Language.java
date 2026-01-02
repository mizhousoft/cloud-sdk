package com.mizhousoft.cloudsdk.tencent.auth;

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

	private final String lang;

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

	public String getValue()
	{
		return this.lang;
	}
}