package com.mizhousoft.tencent.oss;

import com.mizhousoft.cloudsdk.oss.OSSProfile;

/**
 * Profile
 *
 * @version
 */
public class COSProfile extends OSSProfile
{
	// sessionToken
	private String sessionToken;

	/**
	 * 获取sessionToken
	 * 
	 * @return
	 */
	public String getSessionToken()
	{
		return sessionToken;
	}

	/**
	 * 设置sessionToken
	 * 
	 * @param sessionToken
	 */
	public void setSessionToken(String sessionToken)
	{
		this.sessionToken = sessionToken;
	}
}
