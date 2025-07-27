package com.mizhousoft.cloudsdk.huawei.cdn.response;

import java.util.List;

import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 历史任务响应
 *
 * @version
 */
public class ShowHistoryTasksResponse extends CDNResponse
{
	/**
	 * 总共的任务个数。
	 */
	@QueryParam(value = "total")
	private Integer total;

	/**
	 * 日志列表数据
	 */
	@QueryParam(value = "tasks")
	private List<TasksObject> tasks;

	/**
	 * 获取total
	 * 
	 * @return
	 */
	public Integer getTotal()
	{
		return total;
	}

	/**
	 * 设置total
	 * 
	 * @param total
	 */
	public void setTotal(Integer total)
	{
		this.total = total;
	}

	/**
	 * 获取tasks
	 * 
	 * @return
	 */
	public List<TasksObject> getTasks()
	{
		return tasks;
	}

	/**
	 * 设置tasks
	 * 
	 * @param tasks
	 */
	public void setTasks(List<TasksObject> tasks)
	{
		this.tasks = tasks;
	}

}
