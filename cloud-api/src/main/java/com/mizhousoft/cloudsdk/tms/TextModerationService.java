package com.mizhousoft.cloudsdk.tms;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 文本内容服务
 *
 * @version
 */
public interface TextModerationService
{
	/**
	 * 执行
	 * 
	 * @param text
	 * @return
	 * @throws CloudSDKException
	 */
	TextModerationResult execute(String... text) throws CloudSDKException;
}
