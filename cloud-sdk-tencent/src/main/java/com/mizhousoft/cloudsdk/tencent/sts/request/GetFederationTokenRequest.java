package com.mizhousoft.cloudsdk.tencent.sts.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GetFederationToken Request
 *
 * @version
 */
public class GetFederationTokenRequest
{
	/**
	 * 您可以自定义调用方英文名称，由字母组成。
	 */
	@JsonProperty("Name")
	private String name;

	/**
	 * 注意：
	 * 1、策略语法参照[ CAM 策略语法](https://cloud.tencent.com/document/product/598/10603)。
	 * 2、策略中不能包含 principal 元素。
	 * 3、该参数需要做urlencode，服务端会对该字段做urldecode， 并按处理后Policy授予临时访问凭证权限，请按规范传入参数。
	 */
	@JsonProperty("Policy")
	private String policy;

	/**
	 * 指定临时证书的有效期，单位：秒，默认1800秒，主账号最长可设定有效期为7200秒，子账号最长可设定有效期为129600秒。
	 */
	@JsonProperty("DurationSeconds")
	private Long durationSeconds;

	/**
	 * 获取name
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 获取policy
	 * 
	 * @return
	 */
	public String getPolicy()
	{
		return policy;
	}

	/**
	 * 设置policy
	 * 
	 * @param policy
	 */
	public void setPolicy(String policy)
	{
		this.policy = policy;
	}

	/**
	 * 获取durationSeconds
	 * 
	 * @return
	 */
	public Long getDurationSeconds()
	{
		return durationSeconds;
	}

	/**
	 * 设置durationSeconds
	 * 
	 * @param durationSeconds
	 */
	public void setDurationSeconds(Long durationSeconds)
	{
		this.durationSeconds = durationSeconds;
	}
}
