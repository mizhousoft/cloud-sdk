package com.mizhousoft.cloudsdk.tencent.tms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 位置
 *
 * @version
 */
public class Positions
{
	/**
	 * 关键词起始位置
	 */
	@JsonProperty("Start")
	private Long start;

	/**
	 * 关键词结束位置
	 */
	@JsonProperty("End")
	private Long end;

	/**
	 * 获取start
	 * 
	 * @return
	 */
	public Long getStart()
	{
		return start;
	}

	/**
	 * 设置start
	 * 
	 * @param start
	 */
	public void setStart(Long start)
	{
		this.start = start;
	}

	/**
	 * 获取end
	 * 
	 * @return
	 */
	public Long getEnd()
	{
		return end;
	}

	/**
	 * 设置end
	 * 
	 * @param end
	 */
	public void setEnd(Long end)
	{
		this.end = end;
	}
}
