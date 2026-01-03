package com.mizhousoft.cloudsdk.tencent.sms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发生状态
 *
 * @version
 */
public class SendStatus
{
	/**
	 * 发送流水号。
	 */
	@JsonProperty("SerialNo")
	private String serialNo;

	/**
	 * 手机号码，E.164标准，+[国家或地区码][手机号] ，示例如：+8618501234444， 其中前面有一个+号 ，86为国家码，18501234444为手机号。
	 */
	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	/**
	 * 计费条数，计费规则请查询 [计费策略](https://cloud.tencent.com/document/product/382/36135)。
	 */
	@JsonProperty("Fee")
	private Long fee;

	/**
	 * 用户Session内容。
	 */
	@JsonProperty("SessionContext")
	private String sessionContext;

	/**
	 * 短信请求错误码，具体含义请参考
	 * [错误码](https://cloud.tencent.com/document/product/382/59177#.E7.9F.AD.E4.BF.A1-API-3.0-.E5.8F.91.E9.80.81.E9.94.99.E8.AF.AF.E7.A0.81)。
	 */
	@JsonProperty("Code")
	private String code;

	/**
	 * 短信请求错误码描述。
	 */
	@JsonProperty("Message")
	private String message;

	/**
	 * 国家码或地区码，例如CN,US等，对于未识别出国家码或者地区码，默认返回DEF,具体支持列表请参考国际/港澳台计费总览。
	 */
	@JsonProperty("IsoCode")
	private String isoCode;

	/**
	 * 获取serialNo
	 * 
	 * @return
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * 设置serialNo
	 * 
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	/**
	 * 获取phoneNumber
	 * 
	 * @return
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * 设置phoneNumber
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 获取fee
	 * 
	 * @return
	 */
	public Long getFee()
	{
		return fee;
	}

	/**
	 * 设置fee
	 * 
	 * @param fee
	 */
	public void setFee(Long fee)
	{
		this.fee = fee;
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
	 * 获取code
	 * 
	 * @return
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * 设置code
	 * 
	 * @param code
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * 获取message
	 * 
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * 设置message
	 * 
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * 获取isoCode
	 * 
	 * @return
	 */
	public String getIsoCode()
	{
		return isoCode;
	}

	/**
	 * 设置isoCode
	 * 
	 * @param isoCode
	 */
	public void setIsoCode(String isoCode)
	{
		this.isoCode = isoCode;
	}
}
