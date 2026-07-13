package com.mizhousoft.boot.sms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mizhousoft.boot.DemoApplication;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationClient;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationFactory;
import com.mizhousoft.cloudsdk.sms2.SmsException;
import com.mizhousoft.cloudsdk.sms2.SmsTemplate;

/**
 * SmsApplication Test
 *
 * @version
 */
@SpringBootTest(classes = DemoApplication.class)
public class SmsApplicationTest
{
	@Autowired
	private SmsApplicationFactory smsApplicationFactory;

	@Test
	public void sendVerificationCode() throws SmsException
	{
		String templateCode = "register";
		String signName = "";
		Object templateId = "";
		SmsTemplate smsTemplate = new SmsTemplate(templateCode, signName, templateId);

		SmsApplicationClient smsAppClient = smsApplicationFactory.getByName("default");
		smsAppClient.addSmsTemplate(smsTemplate);

		String phoneNumber = "";
		String host = "127.0.0.1";
		smsAppClient.sendVerificationCode(phoneNumber, host, templateCode);
	}
}
