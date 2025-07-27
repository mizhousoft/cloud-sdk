package com.mizhousoft.cloudsdk.huawei.cdn.request;

import com.mizhousoft.cloudsdk.huawei.core.QueryRequest;
import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 查询刷新预热任务请求
 *
 * @version
 */
public class ShowHistoryTasksRequest implements QueryRequest
{
	/**
	 * 当用户开启企业项目功能时，该参数生效，表示查询资源所属项目，"all"表示所有项目。注意：当使用子账号调用接口时，该参数必传。
	 * 您可以通过调用企业项目管理服务（EPS）的查询企业项目列表接口（ListEnterpriseProject）查询企业项目id。
	 */
	@QueryParam(value = "enterprise_project_id")
	private String enterpriseProjectId;

	/**
	 * 单页最大数量，取值范围为1-10000。page_size和page_number必须同时传值。默认值30
	 */
	@QueryParam(value = "page_size")
	private Integer pageSize;

	/**
	 * 当前查询第几页，取值范围为1-65535。默认值1。
	 */
	@QueryParam(value = "page_number")
	private Integer pageNumber;

	/**
	 * 任务状态。 task_inprocess 表示任务处理中，task_done表示任务完成。
	 */
	@QueryParam(value = "status")
	private String status;

	/**
	 * 查询起始时间，相对于UTC 1970-01-01到当前时间相隔的毫秒数。
	 */
	@QueryParam(value = "start_date")
	private Long startDate;

	/**
	 * 查询结束时间，相对于UTC 1970-01-01到当前时间相隔的毫秒数。
	 */
	@QueryParam(value = "end_date")
	private Long endDate;

	/**
	 * 用来排序的字段，支持的字段有“task_type”：任务的类型，“total”：url总数，“processing”：处理中的url个数，
	 * “succeed”：成功处理的url个数，“failed”：处理失败的url个数，“create_time”：任务的创建时间。order_field和order_type必须同时传值，否则使用默认值"create_time"
	 * 和 "desc"：降序。
	 */
	@QueryParam(value = "order_field")
	private String orderField;

	/**
	 * desc：降序，或者asc：升序。默认值desc。
	 */
	@QueryParam(value = "order_type")
	private String orderType;

	/**
	 * 文件类型，file：文件；directory：目录。
	 */
	@QueryParam(value = "file_type")
	private String fileType;

	/**
	 * 任务类型，refresh：刷新任务；preheating：预热任务。
	 */
	@QueryParam(value = "task_type")
	private String taskType;

	/**
	 * 获取enterpriseProjectId
	 * 
	 * @return
	 */
	public String getEnterpriseProjectId()
	{
		return enterpriseProjectId;
	}

	/**
	 * 设置enterpriseProjectId
	 * 
	 * @param enterpriseProjectId
	 */
	public void setEnterpriseProjectId(String enterpriseProjectId)
	{
		this.enterpriseProjectId = enterpriseProjectId;
	}

	/**
	 * 获取pageSize
	 * 
	 * @return
	 */
	public Integer getPageSize()
	{
		return pageSize;
	}

	/**
	 * 设置pageSize
	 * 
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize)
	{
		this.pageSize = pageSize;
	}

	/**
	 * 获取pageNumber
	 * 
	 * @return
	 */
	public Integer getPageNumber()
	{
		return pageNumber;
	}

	/**
	 * 设置pageNumber
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(Integer pageNumber)
	{
		this.pageNumber = pageNumber;
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
	 * 获取startDate
	 * 
	 * @return
	 */
	public Long getStartDate()
	{
		return startDate;
	}

	/**
	 * 设置startDate
	 * 
	 * @param startDate
	 */
	public void setStartDate(Long startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * 获取endDate
	 * 
	 * @return
	 */
	public Long getEndDate()
	{
		return endDate;
	}

	/**
	 * 设置endDate
	 * 
	 * @param endDate
	 */
	public void setEndDate(Long endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * 获取orderField
	 * 
	 * @return
	 */
	public String getOrderField()
	{
		return orderField;
	}

	/**
	 * 设置orderField
	 * 
	 * @param orderField
	 */
	public void setOrderField(String orderField)
	{
		this.orderField = orderField;
	}

	/**
	 * 获取orderType
	 * 
	 * @return
	 */
	public String getOrderType()
	{
		return orderType;
	}

	/**
	 * 设置orderType
	 * 
	 * @param orderType
	 */
	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
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
}
