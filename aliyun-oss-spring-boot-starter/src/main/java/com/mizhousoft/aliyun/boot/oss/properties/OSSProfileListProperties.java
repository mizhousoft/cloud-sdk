package com.mizhousoft.aliyun.boot.oss.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @version
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProfileListProperties
{
	private List<OSSProfileProperties> list;

	/**
	 * 获取list
	 * 
	 * @return
	 */
	public List<OSSProfileProperties> getList()
	{
		return list;
	}

	/**
	 * 设置list
	 * 
	 * @param list
	 */
	public void setList(List<OSSProfileProperties> list)
	{
		this.list = list;
	}
}
