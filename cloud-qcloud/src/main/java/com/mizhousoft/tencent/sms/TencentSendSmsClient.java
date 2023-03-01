package com.mizhousoft.tencent.sms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SendSmsClient;
import com.mizhousoft.cloudsdk.sms.SmsProfile;
import com.mizhousoft.cloudsdk.sms.SmsSendException;
import com.mizhousoft.cloudsdk.util.AssertUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;

/**
 * 发送短信客户端
 *
 * @version
 */
public class TencentSendSmsClient implements SendSmsClient
{
	private SmsClient smsClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(String phoneNumber, Map<String, String> paramMap, String appId, CloudSmsTemplate smsTemplate) throws SmsSendException
	{
		String[] phoneNumbers = { phoneNumber };

		multiSend(phoneNumbers, paramMap, appId, smsTemplate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String appId, CloudSmsTemplate smsTemplate)
	        throws SmsSendException
	{
		SendSmsRequest req = new SendSmsRequest();

		req.setSmsSdkAppid(appId);
		req.setSign(smsTemplate.getSignName());
		req.setTemplateID(smsTemplate.getTemplateId().toString());

		Collection<String> paramValues = paramMap.values();
		String[] templateParamSet = paramValues.toArray(new String[paramValues.size()]);
		req.setTemplateParamSet(templateParamSet);

		req.setPhoneNumberSet(phoneNumbers);

		try
		{
			SendSmsResponse res = smsClient.SendSms(req);

			List<String> failedPhoneNumbers = new ArrayList<>(5);
			String message = null;

			SendStatus[] statusList = res.getSendStatusSet();
			for (SendStatus status : statusList)
			{
				if (!"ok".equalsIgnoreCase(status.getCode()))
				{
					failedPhoneNumbers.add(status.getPhoneNumber());
					message = status.getMessage();
				}
			}

			if (!failedPhoneNumbers.isEmpty())
			{
				SmsSendException exception = new SmsSendException(message);
				exception.setFailedPhoneNumbers(failedPhoneNumbers.toArray(new String[failedPhoneNumbers.size()]));

				throw exception;
			}
		}
		catch (TencentCloudSDKException e)
		{
			throw new SmsSendException("Send sms failed.", e);
		}
	}

	public void init(SmsProfile profile) throws CloudSDKException
	{
		AssertUtils.notNull(profile.getAccessKeyId(), "Access key id is null.");
		AssertUtils.notNull(profile.getAccessKeySecret(), "Access key secret is null.");
		AssertUtils.notNull(profile.getEndpoint(), "Endpoint is null.");
		AssertUtils.notNull(profile.getRegion(), "Region is null.");

		Credential cred = new Credential(profile.getAccessKeyId(), profile.getAccessKeySecret());

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(profile.getEndpoint());

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		smsClient = new SmsClient(cred, profile.getRegion(), clientProfile);
	}
}
