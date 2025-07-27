package com.mizhousoft.cloudsdk.huawei.cdn.response;

import java.util.List;

import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 历史任务响应
 *
 * @version
 */
public class ShowHistoryTasksResponse
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
	 * 
	 */
	@QueryParam(value = "X-Request-Id")
	private String xRequestId;

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

	/**
	 * 获取xRequestId
	 * 
	 * @return
	 */
	public String getxRequestId()
	{
		return xRequestId;
	}

	/**
	 * 设置xRequestId
	 * 
	 * @param xRequestId
	 */
	public void setxRequestId(String xRequestId)
	{
		this.xRequestId = xRequestId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return "ShowHistoryTasksResponse [total=" + total + ", tasks=" + tasks + ", xRequestId=" + xRequestId + "]";
	}
}
