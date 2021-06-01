package com.mizhousoft.cloudsdk.nlp;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * 识别结果
 *
 * @version
 */
public class RecognitionResult
{
	private List<String> sensitiveWords;

	public boolean isContainSensitiveWord()
	{
		return !CollectionUtils.isEmpty(sensitiveWords);
	}

	/**
	 * 获取sensitiveWords
	 * 
	 * @return
	 */
	public List<String> getSensitiveWords()
	{
		return sensitiveWords;
	}

	/**
	 * 设置sensitiveWords
	 * 
	 * @param sensitiveWords
	 */
	public void setSensitiveWords(List<String> sensitiveWords)
	{
		this.sensitiveWords = sensitiveWords;
	}
}
