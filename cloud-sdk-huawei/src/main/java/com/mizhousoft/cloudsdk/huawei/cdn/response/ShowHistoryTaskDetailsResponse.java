package com.mizhousoft.cloudsdk.huawei.cdn.response;

import java.util.List;

/**
 * 查询历史任务详情响应
 *
 * @version
 */
public class ShowHistoryTaskDetailsResponse
{
	/**
	 * 任务id。
	 */
	private String id;

	/**
	 * 任务类型，refresh：刷新任务；preheating：预热任务
	 */
	private String taskType;

	/**
	 * 任务执行结果,task_done:成功，task_inprocess:处理中
	 */
	private String status;

	/**
	 * 本次提交的url列表
	 */
	private List<UrlObject> urls;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 处理中的url个数
	 */
	private Integer processing;

	/**
	 * 成功处理的url个数
	 */
	private Integer succeed;

	/**
	 * 处理失败的url个数
	 */
	private Integer failed;

	/**
	 * 总的url个数
	 */
	private Integer total;

	/**
	 * 文件类型，file：文件；directory：目录，默认是文件file
	 */
	private String fileType;

	/**
	 * 请求ID
	 */
	private String xRequestId;

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
	 * 获取urls
	 * 
	 * @return
	 */
	public List<UrlObject> getUrls()
	{
		return urls;
	}

	/**
	 * 设置urls
	 * 
	 * @param urls
	 */
	public void setUrls(List<UrlObject> urls)
	{
		this.urls = urls;
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
	 * 获取processing
	 * 
	 * @return
	 */
	public Integer getProcessing()
	{
		return processing;
	}

	/**
	 * 设置processing
	 * 
	 * @param processing
	 */
	public void setProcessing(Integer processing)
	{
		this.processing = processing;
	}

	/**
	 * 获取succeed
	 * 
	 * @return
	 */
	public Integer getSucceed()
	{
		return succeed;
	}

	/**
	 * 设置succeed
	 * 
	 * @param succeed
	 */
	public void setSucceed(Integer succeed)
	{
		this.succeed = succeed;
	}

	/**
	 * 获取failed
	 * 
	 * @return
	 */
	public Integer getFailed()
	{
		return failed;
	}

	/**
	 * 设置failed
	 * 
	 * @param failed
	 */
	public void setFailed(Integer failed)
	{
		this.failed = failed;
	}

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
	 * 获取fileType
	 * 
	 * @return
	 */
	public String getFileType()
	{
		return fileType;
	}

	/**
	 * 设置fileType
	 * 
	 * @param fileType
	 */
	public void setFileType(String fileType)
	{
		this.fileType = fileType;
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
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (id != null)
		{
			builder.append("id\":\"").append(id).append("\", \"");
		}
		if (taskType != null)
		{
			builder.append("taskType\":\"").append(taskType).append("\", \"");
		}
		if (status != null)
		{
			builder.append("status\":\"").append(status).append("\", \"");
		}
		if (urls != null)
		{
			builder.append("urls\":\"").append(urls).append("\", \"");
		}
		if (createTime != null)
		{
			builder.append("createTime\":\"").append(createTime).append("\", \"");
		}
		if (processing != null)
		{
			builder.append("processing\":\"").append(processing).append("\", \"");
		}
		if (succeed != null)
		{
			builder.append("succeed\":\"").append(succeed).append("\", \"");
		}
		if (failed != null)
		{
			builder.append("failed\":\"").append(failed).append("\", \"");
		}
		if (total != null)
		{
			builder.append("total\":\"").append(total).append("\", \"");
		}
		if (fileType != null)
		{
			builder.append("fileType\":\"").append(fileType).append("\", \"");
		}
		if (xRequestId != null)
		{
			builder.append("xRequestId\":\"").append(xRequestId);
		}
		builder.append("\"}");
		return builder.toString();
	}

}
