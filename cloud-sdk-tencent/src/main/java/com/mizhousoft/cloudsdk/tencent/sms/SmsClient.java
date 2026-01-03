package com.mizhousoft.cloudsdk.tencent.sms;

import java.util.Map;

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
	 * 发送短信
	 * 
	 * @param phoneNumber
	 * @param paramMap
	 * @param appId
	 * @param sign
	 * @param templateId
	 * @throws CloudSDKException
	 */
	void send(String phoneNumber, Map<String, String> paramMap, String appId, String sign, String templateId) throws CloudSDKException;

	/**
	 * 发送多个
	 * 
	 * @param phoneNumbers
	 * @param paramMap
	 * @param appId
	 * @param sign
	 * @param templateId
	 * @throws CloudSDKException
	 */
	void multiSend(String[] phoneNumbers, Map<String, String> paramMap, String appId, String sign, String templateId)
	        throws CloudSDKException;

	/**
	 * 查询套餐包信息统计
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	SmsPackagesStatisticsResponse querySmsPackageStatistics(SmsPackageStatisticsRequest request) throws CloudSDKException;
}
