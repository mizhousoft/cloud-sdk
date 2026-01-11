package com.mizhousoft.cloudsdk.tencent.tms;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.tms.TextModerationResult;

/**
 * TmsClient
 *
 * @version
 */
public interface TmsClient
{
	/**
	 * 执行
	 * 
	 * @param texts
	 * @return
	 * @throws CloudSDKNewException
	 */
	TextModerationResult execute(String... texts) throws CloudSDKNewException;
}
