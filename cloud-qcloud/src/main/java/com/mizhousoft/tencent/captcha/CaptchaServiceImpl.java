package com.mizhousoft.tencent.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.captcha.CaptchaProfile;
import com.mizhousoft.cloudsdk.captcha.CaptchaRequest;
import com.mizhousoft.cloudsdk.captcha.CaptchaService;
import com.tencentcloudapi.captcha.v20190722.CaptchaClient;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

/**
 * 验证码服务
 *
 * @version
 */
public class CaptchaServiceImpl implements CaptchaService
{
	private static final Logger LOG = LoggerFactory.getLogger("CAPTCHA");

	private static final String DELIMITER = "|";

	private CaptchaClient client;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long verify(CaptchaRequest request) throws CloudSDKException
	{
		DescribeCaptchaResultRequest req = new DescribeCaptchaResultRequest();
		req.setCaptchaType(request.getCaptchaType());
		req.setTicket(request.getTicket());
		req.setUserIp(request.getUserIp());
		req.setRandstr(request.getRandstr());
		req.setCaptchaAppId(request.getCaptchaAppId());
		req.setAppSecretKey(request.getAppSecretKey());

		if (request.isNeedGetCaptchaTime())
		{
			req.setNeedGetCaptchaTime(1L);
		}

		long startTime = System.currentTimeMillis();

		try
		{
			DescribeCaptchaResultResponse resp = client.DescribeCaptchaResult(req);

			long endTime = System.currentTimeMillis();

			writeLog(request, resp, endTime - startTime);

			Long code = resp.getCaptchaCode();
			if (1 != code)
			{
				throw new CloudSDKException("Captcha verify failed, code is " + code + ", msg is " + resp.getCaptchaMsg());
			}

			return resp.getEvilLevel();
		}
		catch (TencentCloudSDKException e)
		{
			long endTime = System.currentTimeMillis();

			writeLog(request, null, endTime - startTime);

			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	private void writeLog(CaptchaRequest request, DescribeCaptchaResultResponse resp, long time)
	{
		StringBuilder buffer = new StringBuilder();

		buffer.append("v1").append(DELIMITER).append(request.getCaptchaType()).append(DELIMITER).append(request.getCaptchaAppId())
		        .append(DELIMITER).append(request.getUserIp()).append(DELIMITER).append(request.getBusinessId()).append(DELIMITER)
		        .append(request.getSceneId());

		if (null != resp)
		{
			buffer.append(DELIMITER).append(resp.getCaptchaCode()).append(DELIMITER).append(resp.getEvilLevel()).append(DELIMITER)
			        .append(resp.getRequestId());
		}
		else
		{
			buffer.append(DELIMITER).append("").append(DELIMITER).append("").append(DELIMITER).append("");
		}

		buffer.append(DELIMITER).append(time);

		LOG.info(buffer.toString());
	}

	public void init(CaptchaProfile profile)
	{
		Credential cred = new Credential(profile.getSecretId(), profile.getSecretKey());

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(profile.getEndpoint());

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		CaptchaClient client = new CaptchaClient(cred, "", clientProfile);

		this.client = client;
	}
}
