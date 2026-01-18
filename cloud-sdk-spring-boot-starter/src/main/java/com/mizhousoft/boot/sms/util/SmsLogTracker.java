package com.mizhousoft.boot.sms.util;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.cloudsdk.sms2.SmsTemplate;

/**
 * 日志追踪
 *
 * @version
 */
public abstract class SmsLogTracker
{
	private static final Logger SMS_LOG = LoggerFactory.getLogger("SMS");

	/**
	 * 记录
	 * 
	 * @param application
	 * @param phoneNumbers
	 * @param succeed
	 * @param smsTemplate
	 */
	public static void log(SmsApplication application, String[] phoneNumbers, boolean succeed, SmsTemplate smsTemplate)
	{
		for (String phoneNumber : phoneNumbers)
		{
			log(application, phoneNumber, succeed, "", smsTemplate);
		}
	}

	/**
	 * 记录
	 * 
	 * @param application
	 * @param phoneNumber
	 * @param succeed
	 * @param smsTemplate
	 */
	public static void log(SmsApplication application, String phoneNumber, boolean succeed, SmsTemplate smsTemplate)
	{

		log(application, phoneNumber, succeed, "", smsTemplate);
	}

	/**
	 * 记录
	 * 
	 * @param application
	 * @param phoneNumber
	 * @param succeed
	 * @param host
	 * @param smsTemplate
	 */
	public static void log(SmsApplication application, String phoneNumber, boolean succeed, String host, SmsTemplate smsTemplate)
	{
		String result = BooleanUtils.toString(succeed, "T", "F");

		StringBuilder buffer = new StringBuilder();
		buffer.append("v1")
		        .append("|")
		        .append(application.getVendor())
		        .append("|")
		        .append(application.getName())
		        .append("|")
		        .append(application.getAppId())
		        .append("|")
		        .append(smsTemplate.getTemplateCode())
		        .append("|")
		        .append(smsTemplate.getTemplateId())
		        .append("|")
		        .append(phoneNumber)
		        .append("|")
		        .append(host)
		        .append("|")
		        .append(result);

		SMS_LOG.info(buffer.toString());
	}

}
