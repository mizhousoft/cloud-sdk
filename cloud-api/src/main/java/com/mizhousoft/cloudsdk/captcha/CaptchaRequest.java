package com.mizhousoft.cloudsdk.captcha;

/**
 * 请求
 *
 * @version
 */
public class CaptchaRequest
{
	// 验证码类型
	private long captchaType;

	// 前端回调函数返回的用户验证票据
	private String ticket;

	// 用户操作来源的外网 IP
	private String userIp;

	// 前端回调函数返回的随机字符串
	private String randstr;

	// 验证码应用ID
	private long captchaAppId;

	// 用于服务器端校验验证码票据的验证密钥，请妥善保密，请勿泄露给第三方
	private String appSecretKey;

	// 业务 ID，网站或应用在多个业务中使用此服务，通过此 ID 区分统计数据
	private long businessId;

	// 场景 ID，网站或应用的业务下有多个场景使用此服务，通过此 ID 区分统计数据
	private long sceneId;

	// 是否返回前端获取验证码时间，取值1：需要返回
	private boolean needGetCaptchaTime;

	/**
	 * 获取captchaType
	 * 
	 * @return
	 */
	public long getCaptchaType()
	{
		return captchaType;
	}

	/**
	 * 设置captchaType
	 * 
	 * @param captchaType
	 */
	public void setCaptchaType(long captchaType)
	{
		this.captchaType = captchaType;
	}

	/**
	 * 获取ticket
	 * 
	 * @return
	 */
	public String getTicket()
	{
		return ticket;
	}

	/**
	 * 设置ticket
	 * 
	 * @param ticket
	 */
	public void setTicket(String ticket)
	{
		this.ticket = ticket;
	}

	/**
	 * 获取userIp
	 * 
	 * @return
	 */
	public String getUserIp()
	{
		return userIp;
	}

	/**
	 * 设置userIp
	 * 
	 * @param userIp
	 */
	public void setUserIp(String userIp)
	{
		this.userIp = userIp;
	}

	/**
	 * 获取randstr
	 * 
	 * @return
	 */
	public String getRandstr()
	{
		return randstr;
	}

	/**
	 * 设置randstr
	 * 
	 * @param randstr
	 */
	public void setRandstr(String randstr)
	{
		this.randstr = randstr;
	}

	/**
	 * 获取captchaAppId
	 * 
	 * @return
	 */
	public long getCaptchaAppId()
	{
		return captchaAppId;
	}

	/**
	 * 设置captchaAppId
	 * 
	 * @param captchaAppId
	 */
	public void setCaptchaAppId(long captchaAppId)
	{
		this.captchaAppId = captchaAppId;
	}

	/**
	 * 获取appSecretKey
	 * 
	 * @return
	 */
	public String getAppSecretKey()
	{
		return appSecretKey;
	}

	/**
	 * 设置appSecretKey
	 * 
	 * @param appSecretKey
	 */
	public void setAppSecretKey(String appSecretKey)
	{
		this.appSecretKey = appSecretKey;
	}

	/**
	 * 获取businessId
	 * 
	 * @return
	 */
	public long getBusinessId()
	{
		return businessId;
	}

	/**
	 * 设置businessId
	 * 
	 * @param businessId
	 */
	public void setBusinessId(long businessId)
	{
		this.businessId = businessId;
	}

	/**
	 * 获取sceneId
	 * 
	 * @return
	 */
	public long getSceneId()
	{
		return sceneId;
	}

	/**
	 * 设置sceneId
	 * 
	 * @param sceneId
	 */
	public void setSceneId(long sceneId)
	{
		this.sceneId = sceneId;
	}

	/**
	 * 获取needGetCaptchaTime
	 * 
	 * @return
	 */
	public boolean isNeedGetCaptchaTime()
	{
		return needGetCaptchaTime;
	}

	/**
	 * 设置needGetCaptchaTime
	 * 
	 * @param needGetCaptchaTime
	 */
	public void setNeedGetCaptchaTime(boolean needGetCaptchaTime)
	{
		this.needGetCaptchaTime = needGetCaptchaTime;
	}
}
