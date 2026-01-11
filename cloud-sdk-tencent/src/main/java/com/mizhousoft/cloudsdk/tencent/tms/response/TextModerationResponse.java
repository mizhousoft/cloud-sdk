package com.mizhousoft.cloudsdk.tencent.tms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.common.TencentResponse;

/**
 * 文本识别响应
 *
 * @version
 */
public class TextModerationResponse extends TencentResponse
{
	/**
	 * 该字段用于回显检测对象请求参数中的 BizType，与输入的 BizType 值对应。
	 */
	@JsonProperty("BizType")
	private String bizType;

	/**
	 * 用于标识对本次请求的处置建议，共三种返回值。
	 * 返回值：**Block**: 建议直接做违规处置，**Review**: 建议人工二次确认，**Pass**: 未识别到风险。
	 */
	@JsonProperty("Suggestion")
	private String suggestion;

	/**
	 * 该字段用于返回检测结果（DetailResults）中所对应的**优先级最高的恶意标签**，表示模型推荐的审核结果，建议您按照业务所需，对不同违规类型与建议值进行处理。<br>
	 * 返回值：**Normal**：正常，**Porn**：色情，**Abuse**：谩骂，**Ad**：广告；以及其他令人反感、不安全或不适宜的内容类型
	 */
	@JsonProperty("Label")
	private String label;

	/**
	 * 对应 Label 字段下的二级子标签，表示该 Label 下更细分的违规点。
	 */
	@JsonProperty("SubLabel")
	private String subLabel;

	/**
	 * 该字段标识 SubLabel 的置信度，取值范围为 0 - 100，值越高代表置信度越高。
	 */
	@JsonProperty("Score")
	private Long score;

	/**
	 * 该字段标识被检测文本所命中的关键词，可能返回0个或多个关键词。
	 * 注意：此字段可能返回 null，表示取不到有效值。
	 */
	@JsonProperty("Keywords")
	private String[] keywords;

	/**
	 * 该字段返回的检测的详细信息，返回值信息可参阅对应数据结构 DetailResults 的详细描述。
	 * 注意：此字段可能返回 null，表示取不到有效值。
	 */
	@JsonProperty("DetailResults")
	private DetailResults[] detailResults;

	/**
	 * 该字段标识入参 User 的检测结果，具体内容参阅数据结构 RiskDetails。
	 * 注意：此字段可能返回 null，表示取不到有效值。
	 */
	@JsonProperty("RiskDetails")
	private RiskDetails[] riskDetails;

	/**
	 * 该字段用于返回根据您的需求配置的附加信息（Extra），如未配置则默认返回值为空。
	 * 备注：不同客户或Biztype下返回信息不同，如需配置该字段请提交工单咨询或联系售后专员处理。
	 */
	@JsonProperty("Extra")
	private String extra;

	/**
	 * 该字段用于回显检测对象请求参数中的 DataId，与输入的 DataId 值对应。
	 */
	@JsonProperty("DataId")
	private String dataId;

	/**
	 * 历史上下文关联的字段，不再推荐使用。上下文关联审核可通过入参的 SessionId 来实现。
	 */
	@JsonProperty("ContextText")
	private String contextText;

	/**
	 * 该字段为历史结构字段，不再推荐使用。
	 */
	@JsonProperty("HitType")
	private String hitType;

	/**
	 * 该字段用于回显检测对象请求参数中的 SessionId，与输入的 SessionId 值对应。
	 */
	@JsonProperty("SessionId")
	private String sessionId;

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
	 * 获取suggestion
	 * 
	 * @return
	 */
	public String getSuggestion()
	{
		return suggestion;
	}

	/**
	 * 设置suggestion
	 * 
	 * @param suggestion
	 */
	public void setSuggestion(String suggestion)
	{
		this.suggestion = suggestion;
	}

	/**
	 * 获取label
	 * 
	 * @return
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * 设置label
	 * 
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * 获取subLabel
	 * 
	 * @return
	 */
	public String getSubLabel()
	{
		return subLabel;
	}

	/**
	 * 设置subLabel
	 * 
	 * @param subLabel
	 */
	public void setSubLabel(String subLabel)
	{
		this.subLabel = subLabel;
	}

	/**
	 * 获取score
	 * 
	 * @return
	 */
	public Long getScore()
	{
		return score;
	}

	/**
	 * 设置score
	 * 
	 * @param score
	 */
	public void setScore(Long score)
	{
		this.score = score;
	}

	/**
	 * 获取keywords
	 * 
	 * @return
	 */
	public String[] getKeywords()
	{
		return keywords;
	}

	/**
	 * 设置keywords
	 * 
	 * @param keywords
	 */
	public void setKeywords(String[] keywords)
	{
		this.keywords = keywords;
	}

	/**
	 * 获取detailResults
	 * 
	 * @return
	 */
	public DetailResults[] getDetailResults()
	{
		return detailResults;
	}

	/**
	 * 设置detailResults
	 * 
	 * @param detailResults
	 */
	public void setDetailResults(DetailResults[] detailResults)
	{
		this.detailResults = detailResults;
	}

	/**
	 * 获取riskDetails
	 * 
	 * @return
	 */
	public RiskDetails[] getRiskDetails()
	{
		return riskDetails;
	}

	/**
	 * 设置riskDetails
	 * 
	 * @param riskDetails
	 */
	public void setRiskDetails(RiskDetails[] riskDetails)
	{
		this.riskDetails = riskDetails;
	}

	/**
	 * 获取extra
	 * 
	 * @return
	 */
	public String getExtra()
	{
		return extra;
	}

	/**
	 * 设置extra
	 * 
	 * @param extra
	 */
	public void setExtra(String extra)
	{
		this.extra = extra;
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
	 * 获取contextText
	 * 
	 * @return
	 */
	public String getContextText()
	{
		return contextText;
	}

	/**
	 * 设置contextText
	 * 
	 * @param contextText
	 */
	public void setContextText(String contextText)
	{
		this.contextText = contextText;
	}

	/**
	 * 获取hitType
	 * 
	 * @return
	 */
	public String getHitType()
	{
		return hitType;
	}

	/**
	 * 设置hitType
	 * 
	 * @param hitType
	 */
	public void setHitType(String hitType)
	{
		this.hitType = hitType;
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
