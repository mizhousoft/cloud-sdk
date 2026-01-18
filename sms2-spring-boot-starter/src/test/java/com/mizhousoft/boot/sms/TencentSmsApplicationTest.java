package com.mizhousoft.boot.sms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mizhousoft.cloudsdk.sms2.SmsApplicationClient;
import com.mizhousoft.cloudsdk.sms2.SmsApplicationFactory;
import com.mizhousoft.cloudsdk.sms2.SmsException;
import com.mizhousoft.cloudsdk.sms2.SmsTemplate;

/**
 * TencentSmsApplication Test
 *
 * @version
 */
@SpringBootTest(classes = DemoApplication.class)
public class TencentSmsApplicationTest
{
	@Autowired
	private SmsApplicationFactory smsApplicationFactory;

	@Test
	public void sendVerificationCode() throws SmsException
	{
		String templateCode = "register";
		String signName = "米舟科技";
		Object templateId = "650126";
		SmsTemplate smsTemplate = new SmsTemplate(templateCode, signName, templateId);

		SmsApplicationClient smsAppClient = smsApplicationFactory.getByName("tencent");
		smsAppClient.addSmsTemplate(smsTemplate);

		String phoneNumber = "+8618902844821";
		String host = "127.0.0.1";
		smsAppClient.sendVerificationCode(phoneNumber, host, templateCode);
	}
}
