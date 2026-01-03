package com.mizhousoft.cloudsdk.tencent.core;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.mizhousoft.cloudsdk.tencent.core.http.HttpProtocol;

import kong.unirest.core.HttpMethod;

/**
 * 请求
 *
 * @version
 */
public interface HttpRequest
{
	/**
	 * 获取名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 获取协议
	 * 
	 * @return
	 */
	HttpProtocol getProtocol();

	/**
	 * 获取Endpoint
	 * 
	 * @return
	 */
	String getEndpoint();

	/**
	 * 获取原始路径
	 * 
	 * @return
	 */
	String getRawPath();

	/**
	 * 获取规范请求路径
	 * 
	 * @return
	 */
	String getCanonicalPath();

	/**
	 * 获取HttpMethod
	 * 
	 * @return
	 */
	HttpMethod getHttpMethod();

	/**
	 * 获取ContentType
	 * 
	 * @return
	 */
	String getContentType();

	/**
	 * 获取Url
	 * 
	 * @return
	 */
	URL getUrl();

	/**
	 * 获取请求Body
	 * 
	 * @return
	 */
	String getStringBody();

	/**
	 * 获取数据流
	 * 
	 * @return
	 */
	InputStream getStreamBody();

	/**
	 * 获取查询参数字符串
	 * 
	 * @return
	 */
	String getQueryParamsString();

	/**
	 * 获取查询查询
	 * 
	 * @return
	 */
	Map<String, List<String>> getQueryParams();

	/**
	 * 获取Header
	 * 
	 * @return
	 */
	Map<String, String> getHeaders();

	/**
	 * 是否包含Header
	 * 
	 * @param name
	 * @return
	 */
	Boolean containHeader(String name);

	/**
	 * 获取Header
	 * 
	 * @param name
	 * @return
	 */
	String getHeader(String name);

	/**
	 * 是否跳过请求签名
	 * 
	 * @return
	 */
	boolean isSkipRequestSign();

	/**
	 * 是否不签名
	 * 
	 * @return
	 */
	boolean isUnsignedPayload();
}
