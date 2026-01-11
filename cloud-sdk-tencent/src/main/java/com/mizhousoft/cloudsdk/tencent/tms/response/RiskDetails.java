package com.mizhousoft.cloudsdk.tencent.tms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 风险详情
 *
 * @version
 */
public class RiskDetails
{
	/**
	 * 该字段用于返回账号信息检测对应的风险类别，取值为：**RiskAccount**（账号存在风险）、**RiskIP**（IP地址存在风险）、**RiskIMEI**（移动设备识别码存在风险）。
	 */
	@JsonProperty("Label")
	private String label;

	/**
	 * 该字段用于返回账号信息检测对应的风险等级，取值为：**1**（疑似存在风险）和**2**（存在恶意风险）。
	 */
	@JsonProperty("Level")
	private Long level;

	/**
	 * 获取label
	 * 
	 * @return
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * 设置label
	 * 
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * 获取level
	 * 
	 * @return
	 */
	public Long getLevel()
	{
		return level;
	}

	/**
	 * 设置level
	 * 
	 * @param level
	 */
	public void setLevel(Long level)
	{
		this.level = level;
	}
}