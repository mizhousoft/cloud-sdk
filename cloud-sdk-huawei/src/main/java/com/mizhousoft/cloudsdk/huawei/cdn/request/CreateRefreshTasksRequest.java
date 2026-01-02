package com.mizhousoft.cloudsdk.huawei.cdn.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.huawei.core.GeneralRequest;

/**
 * 任务请求
 *
 * @version
 */
public class CreateRefreshTasksRequest implements GeneralRequest
{
	/**
	 * 缓存刷新
	 */
	@JsonProperty(value = "refresh_task")
	private RefreshTaskRequestBody refreshTask;

	/**
	 * 获取refreshTask
	 * 
	 * @return
	 */
	public RefreshTaskRequestBody getRefreshTask()
	{
		return refreshTask;
	}

	/**
	 * 设置refreshTask
	 * 
	 * @param refreshTask
	 */
	public void setRefreshTask(RefreshTaskRequestBody refreshTask)
	{
		this.refreshTask = refreshTask;
	}
}
