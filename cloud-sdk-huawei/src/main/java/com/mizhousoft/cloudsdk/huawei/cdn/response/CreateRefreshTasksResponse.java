package com.mizhousoft.cloudsdk.huawei.cdn.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 创建刷新任务响应
 *
 * @version
 */
public class CreateRefreshTasksResponse extends CDNResponse
{
	/**
	 * 任务ID
	 */
	@JsonProperty(value = "refresh_task")
	private String refreshTask;

	/**
	 * 获取refreshTask
	 * 
	 * @return
	 */
	public String getRefreshTask()
	{
		return refreshTask;
	}

	/**
	 * 设置refreshTask
	 * 
	 * @param refreshTask
	 */
	public void setRefreshTask(String refreshTask)
	{
		this.refreshTask = refreshTask;
	}
}
