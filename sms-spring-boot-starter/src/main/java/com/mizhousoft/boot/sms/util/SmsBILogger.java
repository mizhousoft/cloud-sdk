package com.mizhousoft.boot.sms.util;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.sms.CloudSmsTemplate;

/**
 * Logger
 *
 * @version
 */
public abstract class SmsBILogger
{
	private static final Logger SMS_LOG = LoggerFactory.getLogger("SMS");

	public static void log(String[] phoneNumbers, boolean succeed, CloudSmsTemplate smsTemplate)
	{
		for (String phoneNumber : phoneNumbers)
		{
			log(phoneNumber, succeed, "", smsTemplate);
		}
	}

	public static void log(String phoneNumber, boolean succeed, CloudSmsTemplate smsTemplate)
	{

		log(phoneNumber, succeed, "", smsTemplate);
	}

	public static void log(String phoneNumber, boolean succeed, String host, CloudSmsTemplate smsTemplate)
	{
		String result = BooleanUtils.toString(succeed, "T", "F");

		StringBuilder buffer = new StringBuilder();
		buffer.append(smsTemplate.getTemplateCode()).append("|").append(smsTemplate.getTemplateId()).append("|").append(phoneNumber)
		        .append("|").append(host).append("|").append(result);

		SMS_LOG.info(buffer.toString());
	}

}
