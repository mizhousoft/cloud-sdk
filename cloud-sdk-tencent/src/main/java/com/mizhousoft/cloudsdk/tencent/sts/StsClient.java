package com.mizhousoft.cloudsdk.tencent.sts;

import com.mizhousoft.cloudsdk.CloudSDKException;
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
	 * @throws CloudSDKException
	 */
	TempCredential getFederationToken(GetFederationTokenRequest req) throws CloudSDKException;
}
