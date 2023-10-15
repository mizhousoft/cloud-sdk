package com.mizhousoft.boot.sms.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mizhousoft.boot.sms.model.HostPhoneNumberPool;
import com.mizhousoft.boot.sms.properties.SmsApplication;
import com.mizhousoft.boot.sms.service.SmsWindControlService;

/**
 * 短信风控服务
 *
 * @version
 */
public class SmsWindControlServiceImpl implements SmsWindControlService
{
	private static final Logger LOG = LoggerFactory.getLogger(SmsWindControlServiceImpl.class);

	// <Host, HostPhoneNumberPool>
	private Cache<String, HostPhoneNumberPool> cache = Caffeine.newBuilder().expireAfterWrite(24, TimeUnit.HOURS).build();

	private SmsApplication smsApplication;

	/**
	 * 构造函数
	 *
	 * @param smsApplication
	 */
	public SmsWindControlServiceImpl(SmsApplication smsApplication)
	{
		super();
		this.smsApplication = smsApplication;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean determineRequestOk(String phoneNumber, String host)
	{
		if (!smsApplication.isLimitEnable())
		{
			return true;
		}

		HostPhoneNumberPool pool = cache.getIfPresent(host);
		if (null == pool)
		{
			pool = new HostPhoneNumberPool(host, phoneNumber);
			cache.put(host, pool);
		}
		else
		{
			List<String> phoneNumbers = pool.getPhoneNumbers();
			if (phoneNumbers.size() > smsApplication.getHostMaxRequest())
			{
				LOG.error("The host {} has taken too many SMS messages, phone numbers are {}.", host,
				        StringUtils.join(phoneNumbers.iterator(), ","));
				return false;
			}
			else
			{
				if (!pool.getPhoneNumbers().contains(phoneNumber))
				{
					phoneNumbers.add(phoneNumber);

					cache.invalidate(host);
					cache.put(host, pool);
				}
			}
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidate(String phoneNumber, String host)
	{
		HostPhoneNumberPool pool = cache.getIfPresent(host);
		if (null != pool)
		{
			List<String> phoneNumbers = pool.getPhoneNumbers();
			if (phoneNumbers.size() == 1)
			{
				cache.invalidate(host);
			}
			else
			{
				phoneNumbers.remove(host);

				cache.invalidate(host);
				cache.put(host, pool);
			}
		}
	}
}
