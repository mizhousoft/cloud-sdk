package com.mizhousoft.cloudsdk.huawei.core.http;

/**
 * 常量
 *
 * @version
 */
public interface HeaderConstants
{
	/**
	 * 行分隔符
	 */
	String LINE_SEPARATOR = "\n";

	/**
	 * 签名算法
	 */
	String SDK_HMAC_SHA256 = "SDK-HMAC-SHA256";

	/**
	 * 签名算法
	 */
	String X_SDK_CONTENT_SHA256 = "X-Sdk-Content-Sha256";

	/**
	 * Host
	 */
	String HOST = "host";

	/**
	 * Header
	 */
	String X_SDK_DATE = "X-Sdk-Date";

	/**
	 * Header
	 */
	String ISO_8601_BASIC_FORMAT = "yyyyMMdd'T'HHmmss'Z'";

	/**
	 * Header
	 */
	String CONTENT_TYPE = "Content-Type";

	/**
	 * Header
	 */
	String AUTHORIZATION = "Authorization";

	/**
	 * Header
	 */
	String X_REQUEST_ID = "x-request-id";
}
