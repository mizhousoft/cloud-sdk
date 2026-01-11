package com.mizhousoft.cloudsdk.tencent.tms.response;

import javax.swing.text.html.HTML.Tag;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 详情结果
 *
 * @version
 */
public class DetailResults
{
	/**
	 * 该字段用于返回检测结果所对应的全部恶意标签。<br>
	 * 返回值：**Normal**：正常，**Porn**：色情，**Abuse**：谩骂，**Ad**：广告；以及其他令人反感、不安全或不适宜的内容类型。
	 */
	@JsonProperty("Label")
	private String label;

	/**
	 * 该字段用于返回对应当前标签的后续操作建议。当您获取到判定结果后，返回值表示系统推荐的后续操作；建议您按照业务所需，对不同违规类型与建议值进行处理。<br>
	 * 返回值：**Block**：建议屏蔽，**Review** ：建议人工复审，**Pass**：建议通过
	 */
	@JsonProperty("Suggestion")
	private String suggestion;

	/**
	 * 该字段用于返回检测文本命中的关键词信息，用于标注文本违规的具体原因（如：*加我微信*）。该参数可能会有多个返回值，代表命中的多个关键词；如返回值为空且Score不为空，则代表识别结果所对应的恶意标签（Label）是来自于语义模型判断的返回值。
	 */
	@JsonProperty("Keywords")
	private String[] keywords;

	/**
	 * 该字段用于返回当前标签（Label）下的置信度，取值范围：0（**置信度最低**）-100（**置信度最高** ），越高代表文本越有可能属于当前返回的标签；如：*色情
	 * 99*，则表明该文本非常有可能属于色情内容；*色情 0*，则表明该文本不属于色情内容。
	 */
	@JsonProperty("Score")
	private Long score;

	/**
	 * 该字段用于返回自定义关键词对应的词库类型，取值为**1**（黑白库）和**2**（自定义关键词库），若未配置自定义关键词库,则默认值为1（黑白库匹配）。
	 */
	@JsonProperty("LibType")
	private Long libType;

	/**
	 * 该字段用于返回自定义库的ID，以方便自定义库管理和配置。
	 */
	@JsonProperty("LibId")
	private String libId;

	/**
	 * 该字段用于返回自定义库的名称,以方便自定义库管理和配置。
	 */
	@JsonProperty("LibName")
	private String libName;

	/**
	 * 该字段用于返回当前标签（Label）下的二级标签。
	 */
	@JsonProperty("SubLabel")
	private String subLabel;

	/**
	 * 该字段用于返回当前一级标签（Label）下的关键词、子标签及分数。
	 * 注意：此字段可能返回 null，表示取不到有效值。
	 */
	@JsonProperty("Tags")
	private Tag[] tags;

	/**
	 * 该字段用于返回违规文本命中信息
	 */
	@JsonProperty("HitInfos")
	private HitInfo[] hitInfos;

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
	 * 获取libType
	 * 
	 * @return
	 */
	public Long getLibType()
	{
		return libType;
	}

	/**
	 * 设置libType
	 * 
	 * @param libType
	 */
	public void setLibType(Long libType)
	{
		this.libType = libType;
	}

	/**
	 * 获取libId
	 * 
	 * @return
	 */
	public String getLibId()
	{
		return libId;
	}

	/**
	 * 设置libId
	 * 
	 * @param libId
	 */
	public void setLibId(String libId)
	{
		this.libId = libId;
	}

	/**
	 * 获取libName
	 * 
	 * @return
	 */
	public String getLibName()
	{
		return libName;
	}

	/**
	 * 设置libName
	 * 
	 * @param libName
	 */
	public void setLibName(String libName)
	{
		this.libName = libName;
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
	 * 获取tags
	 * 
	 * @return
	 */
	public Tag[] getTags()
	{
		return tags;
	}

	/**
	 * 设置tags
	 * 
	 * @param tags
	 */
	public void setTags(Tag[] tags)
	{
		this.tags = tags;
	}

	/**
	 * 获取hitInfos
	 * 
	 * @return
	 */
	public HitInfo[] getHitInfos()
	{
		return hitInfos;
	}

	/**
	 * 设置hitInfos
	 * 
	 * @param hitInfos
	 */
	public void setHitInfos(HitInfo[] hitInfos)
	{
		this.hitInfos = hitInfos;
	}

}
