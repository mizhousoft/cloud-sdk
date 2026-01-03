package com.mizhousoft.cloudsdk.tencent.sms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;

/**
 * 发送响应
 *
 * @version
 */
public class SendSmsResponse extends GeneralResponse
{
	/**
	 * 短信发送状态。
	 * 注：可参考 <a href="#4.-.E7.A4.BA.E4.BE.8B">示例</a> ，包含短信发送成功和发送失败的输出示例。
	 */
	@JsonProperty("SendStatusSet")
	private SendStatus[] sendStatusSet;

	/**
	 * 获取sendStatusSet
	 * 
	 * @return
	 */
	public SendStatus[] getSendStatusSet()
	{
		return sendStatusSet;
	}

	/**
	 * 设置sendStatusSet
	 * 
	 * @param sendStatusSet
	 */
	public void setSendStatusSet(SendStatus[] sendStatusSet)
	{
		this.sendStatusSet = sendStatusSet;
	}
}
