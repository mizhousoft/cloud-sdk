package com.mizhousoft.cloudsdk.tencent.sts;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.TempCredential;
import com.mizhousoft.cloudsdk.tencent.sts.request.GetFederationTokenRequest;

/**
 * StsClient
 *
 * @version
 */
public interface StsClient
{
	/**
	 * 获取Token
	 * 
	 * @param req
	 * @return
	 * @throws CloudSDKNewException
	 */
	TempCredential getFederationToken(GetFederationTokenRequest req) throws CloudSDKNewException;
}
