package com.mizhousoft.cloudsdk.huawei.cdn;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTaskDetailsRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTaskDetailsResponse;

/**
 * CDN客户端
 *
 * @version
 */
public interface CdnClient
{
	/**
	 * 显示历史任务详情
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	ShowHistoryTaskDetailsResponse showHistoryTaskDetails(ShowHistoryTaskDetailsRequest request) throws CloudSDKException;
}
