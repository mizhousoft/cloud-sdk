package com.mizhousoft.cloudsdk.tencent.sms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发生短信请求
 *
 * @version
 */
public class SendSmsRequest
{
	/**
	 * 下发手机号码，采用 E.164 标准，格式为+[国家或地区码][手机号]，单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。
	 * 例如：+8618501234444， 其中前面有一个+号 ，86为国家码，18501234444为手机号。
	 */
	@JsonProperty("PhoneNumberSet")
	private String[] phoneNumberSet;

	/**
	 * 模板 ID，必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台](https://console.cloud.tencent.com/smsv2)
	 * 查看，若向境外手机号发送短信，仅支持使用国际/港澳台短信模板。
	 */
	@JsonProperty("TemplateID")
	private String templateID;

	/**
	 * 短信SdkAppid在 [短信控制台](https://console.cloud.tencent.com/smsv2)
	 * 添加应用后生成的实际SdkAppid，示例如1400006666。
	 */
	@JsonProperty("SmsSdkAppid")
	private String smsSdkAppid;

	/**
	 * 短信签名内容，使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台](https://console.cloud.tencent.com/smsv2)
	 * 查看。注：国内短信为必填参数。
	 */
	@JsonProperty("Sign")
	private String sign;

	/**
	 * 模板参数，若无模板参数，则设置为空。
	 */
	@JsonProperty("TemplateParamSet")
	private String[] templateParamSet;

	/**
	 * 短信码号扩展号，默认未开通，如需开通请联系 [sms helper](https://cloud.tencent.com/document/product/382/3773)。
	 */
	@JsonProperty("ExtendCode")
	private String extendCode;

	/**
	 * 用户的 session 内容，可以携带用户侧 ID 等上下文信息，server 会原样返回。注意长度需小于512字节。
	 */
	@JsonProperty("SessionContext")
	private String sessionContext;

	/**
	 * 国际/港澳台短信 Sender ID。可参考 [Sender ID 说明](https://cloud.tencent.com/document/product/382/102831)。
	 * 注：国内短信无需填写该项；国际/港澳台短信已申请独立 SenderId 需要填写该字段，默认使用公共 SenderId，无需填写该字段。
	 */
	@JsonProperty("SenderId")
	private String senderId;

	/**
	 * 获取phoneNumberSet
	 * 
	 * @return
	 */
	public String[] getPhoneNumberSet()
	{
		return phoneNumberSet;
	}

	/**
	 * 设置phoneNumberSet
	 * 
	 * @param phoneNumberSet
	 */
	public void setPhoneNumberSet(String[] phoneNumberSet)
	{
		this.phoneNumberSet = phoneNumberSet;
	}

	/**
	 * 获取templateID
	 * 
	 * @return
	 */
	public String getTemplateID()
	{
		return templateID;
	}

	/**
	 * 设置templateID
	 * 
	 * @param templateID
	 */
	public void setTemplateID(String templateID)
	{
		this.templateID = templateID;
	}

	/**
	 * 获取smsSdkAppid
	 * 
	 * @return
	 */
	public String getSmsSdkAppid()
	{
		return smsSdkAppid;
	}

	/**
	 * 设置smsSdkAppid
	 * 
	 * @param smsSdkAppid
	 */
	public void setSmsSdkAppid(String smsSdkAppid)
	{
		this.smsSdkAppid = smsSdkAppid;
	}

	/**
	 * 获取sign
	 * 
	 * @return
	 */
	public String getSign()
	{
		return sign;
	}

	/**
	 * 设置sign
	 * 
	 * @param sign
	 */
	public void setSign(String sign)
	{
		this.sign = sign;
	}

	/**
	 * 获取templateParamSet
	 * 
	 * @return
	 */
	public String[] getTemplateParamSet()
	{
		return templateParamSet;
	}

	/**
	 * 设置templateParamSet
	 * 
	 * @param templateParamSet
	 */
	public void setTemplateParamSet(String[] templateParamSet)
	{
		this.templateParamSet = templateParamSet;
	}

	/**
	 * 获取extendCode
	 * 
	 * @return
	 */
	public String getExtendCode()
	{
		return extendCode;
	}

	/**
	 * 设置extendCode
	 * 
	 * @param extendCode
	 */
	public void setExtendCode(String extendCode)
	{
		this.extendCode = extendCode;
	}

	/**
	 * 获取sessionContext
	 * 
	 * @return
	 */
	public String getSessionContext()
	{
		return sessionContext;
	}

	/**
	 * 设置sessionContext
	 * 
	 * @param sessionContext
	 */
	public void setSessionContext(String sessionContext)
	{
		this.sessionContext = sessionContext;
	}

	/**
	 * 获取senderId
	 * 
	 * @return
	 */
	public String getSenderId()
	{
		return senderId;
	}

	/**
	 * 设置senderId
	 * 
	 * @param senderId
	 */
	public void setSenderId(String senderId)
	{
		this.senderId = senderId;
	}
}
