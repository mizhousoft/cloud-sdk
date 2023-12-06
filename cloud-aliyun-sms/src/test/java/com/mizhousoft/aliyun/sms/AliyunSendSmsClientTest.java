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
	private static SendSmsClient sendSmsClient;

	@BeforeAll
	public static void init() throws CloudSDKException
	{
		SmsProfile profile = new SmsProfile();
		profile.setAccessKeyId("");
		profile.setAccessKeySecret("");
		profile.setEndpoint("dysmsapi.aliyuncs.com");

		AliyunSendSmsClient smsClient = new AliyunSendSmsClient();
		smsClient.init(profile);

		sendSmsClient = smsClient;
	}

	@Test
	public void send()
	{
		String phoneNumber = "";
		Map<String, String> paramMap = Map.of("verificationCode", "123456", "validTime", "10");
		CloudSmsTemplate smsTemplate = new CloudSmsTemplate(null, "", "");

		try
		{
			sendSmsClient.send(phoneNumber, paramMap, null, smsTemplate);
		}
		catch (SmsSendException e)
		{
			Assertions.fail(e);
		}
	}
}
