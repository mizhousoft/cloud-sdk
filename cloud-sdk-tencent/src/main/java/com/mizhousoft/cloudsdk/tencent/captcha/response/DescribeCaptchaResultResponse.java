package com.mizhousoft.cloudsdk.tencent.captcha.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.common.TencentResponse;

/**
 * 验证码校验响应
 *
 * @version
 */
public class DescribeCaptchaResultResponse extends TencentResponse
{
	/**
	 * 验证结果，1：验证成功，0：验证失败，100：AppSecretKey参数校验错误
	 */
	@JsonProperty("CaptchaCode")
	private int captchaCode;

	/**
	 * 验证结果描述信息
	 */
	@JsonProperty("CaptchaMsg")
	private String captchaMsg;

	/**
	 * 恶意等级，0-100
	 */
	@JsonProperty("EvilLevel")
	private int evilLevel;

	/**
	 * 前端获取验证码时间，单位毫秒
	 */
	@JsonProperty("GetCaptchaTime")
	private int getCaptchaTime;

	/**
	 * 拦截类型，注意：此字段可能返回 null，表示取不到有效值
	 */
	@JsonProperty("EvilBitmap")
	private Integer evilBitmap;

	/**
	 * 提交验证码设备信息
	 */
	@JsonProperty("SubmitCaptchaTime")
	private int submitCaptchaTime;

	/**
	 * 唯一请求 ID，由服务端生成，每次请求都会返回
	 */
	@JsonProperty("RequestId")
	private String requestId;

	/**
	 * 获取captchaCode
	 * 
	 * @return
	 */
	public int getCaptchaCode()
	{
		return captchaCode;
	}

	/**
	 * 设置captchaCode
	 * 
	 * @param captchaCode
	 */
	public void setCaptchaCode(int captchaCode)
	{
		this.captchaCode = captchaCode;
	}

	/**
	 * 获取captchaMsg
	 * 
	 * @return
	 */
	public String getCaptchaMsg()
	{
		return captchaMsg;
	}

	/**
	 * 设置captchaMsg
	 * 
	 * @param captchaMsg
	 */
	public void setCaptchaMsg(String captchaMsg)
	{
		this.captchaMsg = captchaMsg;
	}

	/**
	 * 获取evilLevel
	 * 
	 * @return
	 */
	public int getEvilLevel()
	{
		return evilLevel;
	}

	/**
	 * 设置evilLevel
	 * 
	 * @param evilLevel
	 */
	public void setEvilLevel(int evilLevel)
	{
		this.evilLevel = evilLevel;
	}

	/**
	 * 获取getCaptchaTime
	 * 
	 * @return
	 */
	public int getGetCaptchaTime()
	{
		return getCaptchaTime;
	}

	/**
	 * 设置getCaptchaTime
	 * 
	 * @param getCaptchaTime
	 */
	public void setGetCaptchaTime(int getCaptchaTime)
	{
		this.getCaptchaTime = getCaptchaTime;
	}

	/**
	 * 获取evilBitmap
	 * 
	 * @return
	 */
	public Integer getEvilBitmap()
	{
		return evilBitmap;
	}

	/**
	 * 设置evilBitmap
	 * 
	 * @param evilBitmap
	 */
	public void setEvilBitmap(Integer evilBitmap)
	{
		this.evilBitmap = evilBitmap;
	}

	/**
	 * 获取submitCaptchaTime
	 * 
	 * @return
	 */
	public int getSubmitCaptchaTime()
	{
		return submitCaptchaTime;
	}

	/**
	 * 设置submitCaptchaTime
	 * 
	 * @param submitCaptchaTime
	 */
	public void setSubmitCaptchaTime(int submitCaptchaTime)
	{
		this.submitCaptchaTime = submitCaptchaTime;
	}

	/**
	 * 获取requestId
	 * 
	 * @return
	 */
	public String getRequestId()
	{
		return requestId;
	}

	/**
	 * 设置requestId
	 * 
	 * @param requestId
	 */
	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}
}
