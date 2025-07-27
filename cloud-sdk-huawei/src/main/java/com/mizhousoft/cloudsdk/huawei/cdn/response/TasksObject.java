package com.mizhousoft.cloudsdk.huawei.cdn.response;

import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 任务对象
 *
 * @version
 */
public class TasksObject
{
	/**
	 * 任务id。
	 */
	@QueryParam(value = "id")
	private String id;

	/**
	 * 任务的类型， 其值可以为refresh：刷新任务，或preheating：预热任务。
	 */
	@QueryParam(value = "task_type")
	private String taskType;

	/**
	 * 任务执行结果，task_done表示成功 ，task_inprocess表示执行中。
	 */
	@QueryParam(value = "status")
	private String status;

	/**
	 * 处理中的url个数。
	 */
	@QueryParam(value = "processing")
	private Integer processing;

	/**
	 * 成功处理的url个数。
	 */
	@QueryParam(value = "succeed")
	private Integer succeed;

	/**
	 * 处理失败的url个数。
	 */
	@QueryParam(value = "failed")
	private Integer failed;

	/**
	 * url总数
	 */
	@QueryParam(value = "total")
	private Integer total;

	/**
	 * 任务的创建时间，相对于UTC 1970-01-01到当前时间相隔的毫秒数。
	 */
	@QueryParam(value = "create_time")
	private Long createTime;

	/**
	 * 文件类型，file：文件；directory：目录。
	 */
	@QueryParam(value = "file_type")
	private String fileType;

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
}
