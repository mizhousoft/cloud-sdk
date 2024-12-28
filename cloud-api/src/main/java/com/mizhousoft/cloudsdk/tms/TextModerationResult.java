package com.mizhousoft.cloudsdk.tms;

import java.util.List;

import com.mizhousoft.commons.lang.CollectionUtils;

/**
 * 识别结果
 *
 * @version
 */
public class TextModerationResult
{
	/**
	 * 该字段用于返回检测结果（DetailResults）中所对应的优先级最高的恶意标签
	 */
	private String label;

	/**
	 * 该字段用于返回后续操作建议
	 */
	private String suggestion;

	/**
	 * 该字段用于返回当前标签（Label）下被检测文本命中的关键词信息
	 */
	private List<String> keywords;

	public boolean isContainKeyword()
	{
		return !CollectionUtils.isEmpty(keywords);
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
	public List<String> getKeywords()
	{
		return keywords;
	}

	/**
	 * 设置keywords
	 * 
	 * @param keywords
	 */
	public void setKeywords(List<String> keywords)
	{
		this.keywords = keywords;
	}
}
