package com.mizhousoft.cloudsdk.captcha;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 验证码服务
 *
 * @version
 */
public interface CaptchaService
{
	/**
	 * 验证
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	long verify(CaptchaRequest request) throws CloudSDKException;
}
