package com.mizhousoft.aliyun.sms;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SendSmsClient;
import com.mizhousoft.cloudsdk.sms.SmsProfile;
import com.mizhousoft.cloudsdk.sms.SmsSendException;

/**
 * AliyunSendSmsClient Test
 *
 */
public class AliyunSendSmsClientTest
{
	private SendSmsClient sendSmsClient;

	@BeforeAll
	public static void init() throws CloudSDKException
	{
		SmsProfile profile = new SmsProfile();
		profile.setAccessKeyId(null);
		profile.setAccessKeySecret(null);
		profile.setEndpoint(null);

		AliyunSendSmsClient smsClient = new AliyunSendSmsClient();
		smsClient.init(profile);
	}

	@Test
	public void send()
	{
		String phoneNumber = "";
		Map<String, String> paramMap = null;
		String appId = "";
		CloudSmsTemplate smsTemplate = null;

		try
		{
			sendSmsClient.send(phoneNumber, paramMap, appId, smsTemplate);
		}
		catch (SmsSendException e)
		{
			Assertions.fail(e);
		}
	}
}
