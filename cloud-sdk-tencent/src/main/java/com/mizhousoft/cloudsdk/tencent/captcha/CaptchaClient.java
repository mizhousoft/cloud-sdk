package com.mizhousoft.cloudsdk.tencent.captcha;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
import com.mizhousoft.cloudsdk.captcha.CaptchaRequest;
import com.mizhousoft.cloudsdk.tencent.captcha.response.DescribeCaptchaResultResponse;

/**
 * 验证码客户端
 *
 * @version
 */
public interface CaptchaClient
{
	/**
	 * 校验验证码
	 * 
	 * @param request
	 * @return 恶意等级
	 * @throws CloudSDKNewException
	 */
	DescribeCaptchaResultResponse verify(CaptchaRequest request) throws CloudSDKNewException;
}
