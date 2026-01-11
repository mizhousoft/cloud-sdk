package com.mizhousoft.cloudsdk.tencent.sms;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
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
	 * 发送短信
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param appId
	 * @param sign
	 * @param templateId
	 * @throws CloudSDKNewException
	 */
	void send(String phoneNumber, Map<String, String> paramMap, String appId, String sign, String templateId) throws CloudSDKNewException;

	/**
	 * 发送多个
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param appId
	 * @param sign
	 * @param templateId
	 * @throws CloudSDKNewException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String appId, String sign, String templateId)
	        throws CloudSDKNewException;

	/**
	 * 查询套餐包信息统计
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKNewException
	 */
	SmsPackagesStatisticsResponse querySmsPackageStatistics(SmsPackageStatisticsRequest request) throws CloudSDKNewException;
}
