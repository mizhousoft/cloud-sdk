package com.mizhousoft.aliyun.sms;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SendSmsClient;
import com.mizhousoft.cloudsdk.util.AssertUtils;
import com.mizhousoft.commons.json.JSONUtils;

/**
 * 发送短信客户端
 *
 * @version
 */
public class AliyunSendSmsClient implements SendSmsClient
{
	private Client client;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void send(String phoneNumber, Map<String, String> paramMap, CloudSmsTemplate smsTemplate) throws CloudSDKException
	{
		if (null == smsTemplate)
		{
			throw new CloudSDKException("Sms template is null.");
		}

		String signName = smsTemplate.getSignName();
		String templateId = smsTemplate.getTemplateId().toString();

		SendSmsRequest request = new SendSmsRequest();

		request.setPhoneNumbers(phoneNumber);
		request.setSignName(signName);
		request.setTemplateCode(templateId);

		try
		{
			if (null != paramMap)
			{
				String templateParam = JSONUtils.toJSONString(paramMap);
				request.setTemplateParam(templateParam);
			}

			SendSmsResponse result = this.client.sendSms(request);

			SendSmsResponseBody body = result.getBody();
			if (null == body || !"ok".equalsIgnoreCase(body.getCode()))
			{
				throw new CloudSDKException("Send sms failed, code is " + body.code + ", message is " + body.getMessage()
				        + ", request id is " + body.getRequestId() + ", bizId is " + body.getBizId() + '.');
			}
		}
		catch (Exception e)
		{
			throw new CloudSDKException("Send sms failed.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void multiSend(String[] phoneNumbers, Map<String, String> paramMap, CloudSmsTemplate smsTemplate) throws CloudSDKException
	{
		if (null == phoneNumbers || 0 == phoneNumbers.length)
		{
			return;
		}

		String phoneNumber = StringUtils.join(phoneNumbers, ",");
		send(phoneNumber, paramMap, smsTemplate);
	}

	public void init(AliyunSmsProfile profile) throws CloudSDKException
	{
		AssertUtils.notNull(profile.getAccessKeyId(), "Access key id is null.");
		AssertUtils.notNull(profile.getAccessKeySecret(), "Access key secret is null.");
		AssertUtils.notNull(profile.getEndpoint(), "Endpoint is null.");

		Config config = new Config().setAccessKeyId(profile.getAccessKeyId()).setAccessKeySecret(profile.getAccessKeySecret())
		        .setEndpoint(profile.getEndpoint());

		try
		{
			this.client = new com.aliyun.dysmsapi20170525.Client(config);
		}
		catch (Exception e)
		{
			throw new CloudSDKException("Init sms client failed.", e);
		}
	}
}
