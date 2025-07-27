package com.mizhousoft.cloudsdk.huawei.cdn.response;

/**
 * url对象
 *
 * @version
 */
public class UrlObject
{
	/**
	 * url的id
	 */
	private String id;

	/**
	 * 刷新预热url
	 */
	private String url;

	/**
	 * url的状态
	 * processing 处理中，succeed 完成，failed 失败，waiting 等待，refreshing 刷新中，preheating 预热中
	 */
	private String status;

	/**
	 * url创建时间，相对于UTC 1970-01-01到当前时间相隔的毫秒数
	 */
	private Long createTime;

	/**
	 * 任务id
	 */
	private String taskId;

	/**
	 * 任务的类型， 其值可以为
	 * REFRESH：刷新任务、PREHEATING：预热任务、REFRESH_AFTER_PREHEATING：预热后刷新
	 */
	private String taskType;

	/**
	 * 失败原因，url状态为failed时返回。
	 * ORIGIN_ERROR：源站错误。
	 * INNER_ERROR：内部错误。
	 * UNKNOWN_ERROR：未知错误。
	 */
	private String failClassify;

	/**
	 * 刷新预热失败描述
	 */
	private String failDesc;

	/**
	 * 获取id
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * 获取url
	 * 
	 * @return
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * 设置url
	 * 
	 * @param url
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * 获取status
	 * 
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * 设置status
	 * 
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * 获取createTime
	 * 
	 * @return
	 */
	public Long getCreateTime()
	{
		return createTime;
	}

	/**
	 * 设置createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Long createTime)
	{
		this.createTime = createTime;
	}

	/**
	 * 获取taskId
	 * 
	 * @return
	 */
	public String getTaskId()
	{
		return taskId;
	}

	/**
	 * 设置taskId
	 * 
	 * @param taskId
	 */
	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	/**
	 * 获取taskType
	 * 
	 * @return
	 */
	public String getTaskType()
	{
		return taskType;
	}

	/**
	 * 设置taskType
	 * 
	 * @param taskType
	 */
	public void setTaskType(String taskType)
	{
		this.taskType = taskType;
	}

	/**
	 * 获取failClassify
	 * 
	 * @return
	 */
	public String getFailClassify()
	{
		return failClassify;
	}

	/**
	 * 设置failClassify
	 * 
	 * @param failClassify
	 */
	public void setFailClassify(String failClassify)
	{
		this.failClassify = failClassify;
	}

	/**
	 * 获取failDesc
	 * 
	 * @return
	 */
	public String getFailDesc()
	{
		return failDesc;
	}

	/**
	 * 设置failDesc
	 * 
	 * @param failDesc
	 */
	public void setFailDesc(String failDesc)
	{
		this.failDesc = failDesc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (id != null)
		{
			builder.append("id\":\"").append(id).append("\", \"");
		}
		if (url != null)
		{
			builder.append("url\":\"").append(url).append("\", \"");
		}
		if (status != null)
		{
			builder.append("status\":\"").append(status).append("\", \"");
		}
		if (createTime != null)
		{
			builder.append("createTime\":\"").append(createTime).append("\", \"");
		}
		if (taskId != null)
		{
			builder.append("taskId\":\"").append(taskId).append("\", \"");
		}
		if (taskType != null)
		{
			builder.append("taskType\":\"").append(taskType).append("\", \"");
		}
		if (failClassify != null)
		{
			builder.append("failClassify\":\"").append(failClassify).append("\", \"");
		}
		if (failDesc != null)
		{
			builder.append("failDesc\":\"").append(failDesc);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
