package com.mizhousoft.cloudsdk.tencent.tms.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 文本识别请求
 *
 * @version
 */
public class TextModerationRequest
{
	/**
	 * 待检测的文本内容，需为UTF-8编码并以Base64格式传入。
	 * 
	 */
	@JsonProperty("Content")
	private String content;

	/**
	 * 接口使用的识别策略编号，需在[控制台](https://console.cloud.tencent.com/cms/clouds/manage)获取。详细获取方式请参考以下链接：
	 * - **内容安全**（详见步骤四：策略配置）：[点击这里](https://cloud.tencent.com/document/product/1124/37119)
	 * - **AI生成识别**（详见服务对接->方式二）：[点击这里](https://cloud.tencent.com/document/product/1124/118694)
	 */
	@JsonProperty("BizType")
	private String bizType;

	/**
	 * 该字段表示您为待检测文本分配的数据ID，作用是方便您对数据进行标识和管理。
	 * 取值：可由英文字母、数字、四种特殊符号（_，-，@，#）组成，**长度不超过64个字符**。
	 */
	@JsonProperty("DataId")
	private String dataId;

	/**
	 * Content字段的原始语种，枚举值包括 zh 和 en：
	 * - 推荐使用 zh
	 * - en 适用于纯英文内容，耗时较高。若需使用
	 * en，请先通过[反馈工单](https://console.cloud.tencent.com/workorder/category?level1_id=141&level2_id=1287&source=14&data_title=%E6%96%87%E6%9C%AC%E5%86%85%E5%AE%B9%E5%AE%89%E5%85%A8&step=1)确认
	 * 
	 */
	@JsonProperty("SourceLanguage")
	private String sourceLanguage;

	/**
	 * 服务类型，枚举值包括 TEXT 和 TEXT_AIGC：
	 * TEXT：内容安全
	 * TEXT_AIGC：AI生成识别
	 */
	@JsonProperty("Type")
	private String type;

	/**
	 * 适用于上下文关联审核场景，若多条文本内容需要联合审核，通过该字段关联会话。
	 */
	@JsonProperty("SessionId")
	private String sessionId;

	/**
	 * 获取content
	 * 
	 * @return
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 设置content
	 * 
	 * @param content
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 获取bizType
	 * 
	 * @return
	 */
	public String getBizType()
	{
		return bizType;
	}

	/**
	 * 设置bizType
	 * 
	 * @param bizType
	 */
	public void setBizType(String bizType)
	{
		this.bizType = bizType;
	}

	/**
	 * 获取dataId
	 * 
	 * @return
	 */
	public String getDataId()
	{
		return dataId;
	}

	/**
	 * 设置dataId
	 * 
	 * @param dataId
	 */
	public void setDataId(String dataId)
	{
		this.dataId = dataId;
	}

	/**
	 * 获取sourceLanguage
	 * 
	 * @return
	 */
	public String getSourceLanguage()
	{
		return sourceLanguage;
	}

	/**
	 * 设置sourceLanguage
	 * 
	 * @param sourceLanguage
	 */
	public void setSourceLanguage(String sourceLanguage)
	{
		this.sourceLanguage = sourceLanguage;
	}

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
	 * 获取sessionId
	 * 
	 * @return
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * 设置sessionId
	 * 
	 * @param sessionId
	 */
	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}
}
