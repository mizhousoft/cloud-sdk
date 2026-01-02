package com.mizhousoft.cloudsdk.tencent.core.http;

/**
 * MediaType
 *
 * @version
 */
public interface MediaType
{
	/**
	 * JSON
	 */
	String APPLICATION_JSON = "application/json";

	/**
	 * JSON
	 */
	String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";

	/**
	 * 表单
	 */
	String MULTIPART_FORM_DATA = "multipart/form-data";

	/**
	 * 表单
	 */
	String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
}
