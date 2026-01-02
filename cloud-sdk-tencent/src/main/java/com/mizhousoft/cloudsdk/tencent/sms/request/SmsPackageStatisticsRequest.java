package com.mizhousoft.cloudsdk.tencent.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 套餐包信息统计请求
 *
 * @version
 */
public class SmsPackageStatisticsRequest
{
	/**
	 * 短信 SdkAppId 在 短信控制台 添加应用后生成的实际 SdkAppId
	 */
	@JsonProperty("SmsSdkAppId")
	private String smsSdkAppId;

	/**
	 * 起始时间，格式为yyyymmddhh，精确到小时，例如2024050113，表示2024年5月1号13时。
	 * 注：接口会返回 BeginTime 到 EndTime 之间创建的套餐包的统计信息。
	 * 示例值：2024050113
	 */
	@JsonProperty("BeginTime")
	private String beginTime;

	/**
	 * 结束时间，格式为yyyymmddhh，精确到小时，例如2024050118，表示2024年5月1号18时。
	 * 注：EndTime 必须大于 BeginTime 且小于当前时间。
	 * 示例值：2024050118
	 */
	@JsonProperty("EndTime")
	private String endTime;

	/**
	 * 最大上限(需要拉取的套餐包个数)。
	 * 注：Limit默认最大值为500，可结合Offset实现分页查询。
	 * 示例值：2
	 */
	@JsonProperty("Limit")
	private int limit;

	/**
	 * 偏移量。
	 * 示例值：0
	 */
	@JsonProperty("Offset")
	private int offset;

	/**
	 * 获取smsSdkAppId
	 * 
	 * @return
	 */
	public String getSmsSdkAppId()
	{
		return smsSdkAppId;
	}

	/**
	 * 设置smsSdkAppId
	 * 
	 * @param smsSdkAppId
	 */
	public void setSmsSdkAppId(String smsSdkAppId)
	{
		this.smsSdkAppId = smsSdkAppId;
	}

	/**
	 * 获取beginTime
	 * 
	 * @return
	 */
	public String getBeginTime()
	{
		return beginTime;
	}

	/**
	 * 设置beginTime
	 * 
	 * @param beginTime
	 */
	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	/**
	 * 获取endTime
	 * 
	 * @return
	 */
	public String getEndTime()
	{
		return endTime;
	}

	/**
	 * 设置endTime
	 * 
	 * @param endTime
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * 获取limit
	 * 
	 * @return
	 */
	public int getLimit()
	{
		return limit;
	}

	/**
	 * 设置limit
	 * 
	 * @param limit
	 */
	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	/**
	 * 获取offset
	 * 
	 * @return
	 */
	public int getOffset()
	{
		return offset;
	}

	/**
	 * 设置offset
	 * 
	 * @param offset
	 */
	public void setOffset(int offset)
	{
		this.offset = offset;
	}

}
