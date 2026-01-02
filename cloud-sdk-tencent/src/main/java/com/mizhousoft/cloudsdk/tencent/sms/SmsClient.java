package com.mizhousoft.cloudsdk.tencent.sms;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.tencent.sms.request.SmsPackageStatisticsRequest;
import com.mizhousoft.cloudsdk.tencent.sms.response.SmsPackagesStatisticsResponse;

/**
 * 短信客户端
 *
 * @version
 */
public interface SmsClient
{
	/**
	 * 查询套餐包信息统计
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	SmsPackagesStatisticsResponse querySmsPackageStatistics(SmsPackageStatisticsRequest request) throws CloudSDKException;
}
