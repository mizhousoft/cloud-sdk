package com.mizhousoft.cloudsdk.tencent.cdn;

/**
 * CDNClient
 *
 * @version
 */
public interface CDNClient
{
	/**
	 * 签名url
	 * 
	 * @param path
	 * @param uid
	 * @param signExpiredMs
	 * @return
	 */
	String signUrl(String path, long uid, long signExpiredMs);

	/**
	 * 获取endpoint
	 * 
	 * @return
	 */
	String getEndpoint();
}
