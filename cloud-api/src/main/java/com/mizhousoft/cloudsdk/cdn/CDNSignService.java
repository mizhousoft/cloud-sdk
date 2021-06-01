package com.mizhousoft.cloudsdk.cdn;

/**
 * CDN签名服务
 *
 * @version
 */
public interface CDNSignService
{
	/**
	 * 签名url
	 * 
	 * @param path
	 * @param signExpiredMs
	 * @return
	 */
	String signUrl(String path, long signExpiredMs);
}
