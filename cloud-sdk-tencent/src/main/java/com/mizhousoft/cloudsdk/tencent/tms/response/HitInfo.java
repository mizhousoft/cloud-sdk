package com.mizhousoft.cloudsdk.tencent.tms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 命中信息
 *
 * @version
 */
public class HitInfo
{
	/**
	 * 标识模型命中还是关键词命中
	 */
	@JsonProperty("Type")
	private String type;

	/**
	 * 命中关键词
	 */
	@JsonProperty("Keyword")
	private String keyword;

	/**
	 * 自定义词库名称
	 */
	@JsonProperty("LibName")
	private String libName;

	/**
	 * 位置信息
	 */
	@JsonProperty("Positions")
	private Positions[] positions;

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
	 * 获取keyword
	 * 
	 * @return
	 */
	public String getKeyword()
	{
		return keyword;
	}

	/**
	 * 设置keyword
	 * 
	 * @param keyword
	 */
	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
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
	 * 获取positions
	 * 
	 * @return
	 */
	public Positions[] getPositions()
	{
		return positions;
	}

	/**
	 * 设置positions
	 * 
	 * @param positions
	 */
	public void setPositions(Positions[] positions)
	{
		this.positions = positions;
	}
}
