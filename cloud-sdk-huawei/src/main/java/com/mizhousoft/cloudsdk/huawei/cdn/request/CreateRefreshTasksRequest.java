package com.mizhousoft.cloudsdk.huawei.cdn.request;

import com.mizhousoft.cloudsdk.huawei.core.QueryRequest;
import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 任务请求
 *
 * @version
 */
public class CreateRefreshTasksRequest implements QueryRequest
{
	/**
	 * 企业项目id。您可以通过调用企业项目管理服务（EPS）的查询企业项目列表接口（ListEnterpriseProject）查询企业项目id
	 */
	@QueryParam(value = "enterprise_project_id")
	private String enterpriseProjectId;

	/**
	 * 请求Body
	 */
	private RefreshTaskRequest body;

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
	 * 获取body
	 * 
	 * @return
	 */
	public RefreshTaskRequest getBody()
	{
		return body;
	}

	/**
	 * 设置body
	 * 
	 * @param body
	 */
	public void setBody(RefreshTaskRequest body)
	{
		this.body = body;
	}
}
