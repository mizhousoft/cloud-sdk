package com.mizhousoft.tencent.boot.oss.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @version
 */
@Component
@ConfigurationProperties(prefix = "tencent.oss")
public class COSProfileListProperties
{
	private List<COSProfileProperties> list;

	/**
	 * 获取list
	 * 
	 * @return
	 */
	public List<COSProfileProperties> getList()
	{
		return list;
	}

	/**
	 * 设置list
	 * 
	 * @param list
	 */
	public void setList(List<COSProfileProperties> list)
	{
		this.list = list;
	}
}
