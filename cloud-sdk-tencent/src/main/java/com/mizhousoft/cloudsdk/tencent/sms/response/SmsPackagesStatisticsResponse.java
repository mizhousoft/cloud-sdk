package com.mizhousoft.cloudsdk.tencent.sms.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;

/**
 * 套餐包信息统计响应
 *
 * @version
 */
public class SmsPackagesStatisticsResponse extends GeneralResponse
{
	/**
	 * 发送数据统计响应包体。
	 */
	@JsonProperty("SmsPackagesStatisticsSet")
	private List<SmsPackagesStatistics> smsPackagesStatisticsSet;

	/**
	 * 获取smsPackagesStatisticsSet
	 * 
	 * @return
	 */
	public List<SmsPackagesStatistics> getSmsPackagesStatisticsSet()
	{
		return smsPackagesStatisticsSet;
	}

	/**
	 * 设置smsPackagesStatisticsSet
	 * 
	 * @param smsPackagesStatisticsSet
	 */
	public void setSmsPackagesStatisticsSet(List<SmsPackagesStatistics> smsPackagesStatisticsSet)
	{
		this.smsPackagesStatisticsSet = smsPackagesStatisticsSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@JsonProperty("RequestId")
	@Override
	public void setRequestId(String requestId)
	{
		super.setRequestId(requestId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (smsPackagesStatisticsSet != null)
		{
			builder.append("smsPackagesStatisticsSet\":\"");
			builder.append(smsPackagesStatisticsSet);
			builder.append("\", \"");
		}
		if (requestId != null)
		{
			builder.append("requestId\":\"");
			builder.append(requestId);
		}
		builder.append("\"}");
		return builder.toString();
	}

}
