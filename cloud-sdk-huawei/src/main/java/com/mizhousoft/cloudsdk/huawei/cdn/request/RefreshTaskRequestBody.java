package com.mizhousoft.cloudsdk.huawei.cdn.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请求Body
 *
 * @version
 */
public class RefreshTaskRequestBody
{
	/**
	 * 刷新的类型，其值可以为file：文件，或directory：目录，默认为file。
	 */
	@JsonProperty(value = "type")
	private String type;

	/**
	 * 目录刷新方式，all：刷新目录下全部资源；detect_modify_refresh：刷新目录下已变更的资源。默认值为all。
	 */
	@JsonProperty(value = "mode")
	private String mode;

	/**
	 * 是否对url中的中文字符进行编码后刷新，默认值为false。false代表不开启，true代表开启，开启后仅刷新转码后的URL
	 */
	@JsonProperty(value = "zh_url_encode")
	private Boolean zhUrlEncode;

	/**
	 * 需要刷新的URL必须带有“http://”或“https://”，多个URL用逗号分隔（"url1",
	 * "url2"），单个url的长度限制为4096字符，单次最多输入1000个url，如果输入的是目录，支持100个目录刷新。
	 */
	@JsonProperty(value = "urls")
	private List<String> urls;

	/**
	 * 获取type
	 * 
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * 设置type
	 * 
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * 获取mode
	 * 
	 * @return
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * 设置mode
	 * 
	 * @param mode
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * 获取zhUrlEncode
	 * 
	 * @return
	 */
	public Boolean getZhUrlEncode()
	{
		return zhUrlEncode;
	}

	/**
	 * 设置zhUrlEncode
	 * 
	 * @param zhUrlEncode
	 */
	public void setZhUrlEncode(Boolean zhUrlEncode)
	{
		this.zhUrlEncode = zhUrlEncode;
	}

	/**
	 * 获取urls
	 * 
	 * @return
	 */
	public List<String> getUrls()
	{
		return urls;
	}

	/**
	 * 设置urls
	 * 
	 * @param urls
	 */
	public void setUrls(List<String> urls)
	{
		this.urls = urls;
	}
}
