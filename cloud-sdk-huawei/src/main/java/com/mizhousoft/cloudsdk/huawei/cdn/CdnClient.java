package com.mizhousoft.cloudsdk.huawei.cdn;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTaskDetailsRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTasksRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTaskDetailsResponse;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTasksResponse;

/**
 * CDN客户端
 *
 * @version
 */
public interface CdnClient
{
	/**
	 * 查询历史任务
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	ShowHistoryTasksResponse showHistoryTasks(ShowHistoryTasksRequest request) throws CloudSDKException;

	/**
	 * 显示历史任务详情
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	ShowHistoryTaskDetailsResponse showHistoryTaskDetails(ShowHistoryTaskDetailsRequest request) throws CloudSDKException;
}
