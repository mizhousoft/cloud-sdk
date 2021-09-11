package com.mizhousoft.tencent.ocr;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;
import com.mizhousoft.cloudsdk.ocr.OCRProfile;
import com.mizhousoft.cloudsdk.ocr.OCRService;
import com.mizhousoft.tencent.RegionEnum;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sts.v20180813.StsClient;
import com.tencentcloudapi.sts.v20180813.models.Credentials;
import com.tencentcloudapi.sts.v20180813.models.GetFederationTokenRequest;
import com.tencentcloudapi.sts.v20180813.models.GetFederationTokenResponse;

/**
 * OCR服务
 *
 * @version
 */
public class TencentOCRServiceImpl implements OCRService
{
	private static final Logger LOG = LoggerFactory.getLogger(TencentOCRServiceImpl.class);

	private StsClient cosClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempCredential getTempCredential(long durationSecond) throws CloudSDKException
	{
		GetFederationTokenRequest request = new GetFederationTokenRequest();
		request.setName("ocr");
		request.setDurationSeconds(durationSecond);
		request.setPolicy("{\"version\": \"2.0\", \"statement\": [{\"action\": [\"ocr:*\"], \"resource\": \"*\", \"effect\": \"allow\"}]}");

		try
		{
			GetFederationTokenResponse response = cosClient.GetFederationToken(request);

			Credentials credential = response.getCredentials();

			TempCredential tempCredential = new TempCredential();
			tempCredential.setSecretId(credential.getTmpSecretId());
			tempCredential.setSecretKey(credential.getTmpSecretKey());
			tempCredential.setToken(credential.getToken());
			tempCredential.setExpiredTime(response.getExpiredTime());

			return tempCredential;
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getMessage(), e);
		}
	}

	public void init(OCRProfile config) throws CloudSDKException
	{
		validate(config);

		Credential cred = new Credential(config.getAccessKey(), config.getSecretKey());
		StsClient cosClient = new StsClient(cred, config.getRegion());

		this.cosClient = cosClient;

		LOG.info("Init sts client successfully.");
	}

	private void validate(OCRProfile profile) throws CloudSDKException
	{
		if (null == profile)
		{
			throw new CloudSDKException("OCRProfile is null.");
		}

		if (StringUtils.isBlank(profile.getAccessKey()))
		{
			throw new CloudSDKException("Access key is null.");
		}

		if (StringUtils.isBlank(profile.getSecretKey()))
		{
			throw new CloudSDKException("Secret key is null.");
		}

		if (StringUtils.isBlank(profile.getRegion()))
		{
			throw new CloudSDKException("Region is null.");
		}

		RegionEnum region = RegionEnum.get(profile.getRegion());
		if (null == region)
		{
			throw new CloudSDKException("Region does not supported, region is " + profile.getRegion() + '.');
		}
	}
}
