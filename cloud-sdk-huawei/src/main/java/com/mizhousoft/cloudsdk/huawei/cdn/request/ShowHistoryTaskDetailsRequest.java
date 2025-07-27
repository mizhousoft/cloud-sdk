package com.mizhousoft.cloudsdk.huawei.cdn.request;

import com.mizhousoft.cloudsdk.huawei.core.QueryRequest;
import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 查询历史任务详情请求
 *
 * @version
 */
public class ShowHistoryTaskDetailsRequest implements QueryRequest
{
	/**
	 * 企业项目id
	 */
	@QueryParam(value = "enterprise_project_id")
	private String enterpriseProjectId;

	/**
	 * 刷新预热任务ID
	 */
	private String historyTasksId;

	/**
	 * 刷新预热的urls所显示单页最大数量，取值范围为1-10000。
	 * page_size和page_number必须同时传值。默认值30。
	 */
	@QueryParam(value = "page_size")
	private Integer pageSize;

	/**
	 * 刷新预热的urls当前查询为第几页，取值范围为1-65535。默认值1。
	 */
	@QueryParam(value = "page_number")
	private Integer pageNumber;

	/**
	 * url的状态
	 * processing 处理中，succeed 完成，failed 失败，waiting 等待，refreshing 刷新中，preheating 预热中。
	 */
	@QueryParam(value = "status")
	private String status;

	/**
	 * 刷新预热url地址。
	 */
	@QueryParam(value = "url")
	private String url;

	/**
	 * 刷新预热任务的创建时间。不传参默认为查询7天内的任务。最长可查询15天内数据。
	 */
	@QueryParam(value = "create_time")
	private Long createTime;

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
	 * 获取historyTasksId
	 * 
	 * @return
	 */
	public String getHistoryTasksId()
	{
		return historyTasksId;
	}

	/**
	 * 设置historyTasksId
	 * 
	 * @param historyTasksId
	 */
	public void setHistoryTasksId(String historyTasksId)
	{
		this.historyTasksId = historyTasksId;
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
}
