package com.mizhousoft.boot.sms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;
import com.mizhousoft.cloudsdk.sms.SmsApplicationFactory;
import com.mizhousoft.cloudsdk.sms.SmsApplicationService;
import com.mizhousoft.cloudsdk.sms.SmsTemplateContainer;

/**
 * TestAliyunSmsService Test
 *
 * @version
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestAliyunSmsService
{
	@Autowired
	private SmsApplicationFactory smsApplicationFactory;

	@Autowired
	private SmsTemplateContainer smsTemplateContainer;

	@Test
	public void pushNotification() throws CloudSDKException
	{
		String templateCode = "register";
		String signName = "尼欧科技";
		Object templateId = "SMS_147436020";
		CloudSmsTemplate template = new CloudSmsTemplate(templateCode, signName, templateId);
		smsTemplateContainer.register(template);

		SmsApplicationService smsApplicationService = smsApplicationFactory.getByName("general");

		String phoneNumber = "18902844821";
		String host = "127.0.0.1";
		smsApplicationService.sendVerificationCode(phoneNumber, host, templateCode);
	}
}
