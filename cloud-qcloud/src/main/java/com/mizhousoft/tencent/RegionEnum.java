package com.mizhousoft.tencent;

/**
 * 区域枚举
 *
 * @version
 */
public enum RegionEnum
{
    // 广州
	GUANGZHOU("ap-guangzhou"),
    // 广州2
	GUANGZHOU2("ap-guangzhou-2");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private RegionEnum(String val)
	{
		this.value = val;
	}

	/**
	 * 值
	 */
	private final String value;

	/**
	 * 获取value
	 * 
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 获取状态
	 * 
	 * @param status
	 * @return
	 */
	public static RegionEnum get(String value)
	{
		RegionEnum[] values = RegionEnum.values();
		for (RegionEnum val : values)
		{
			if (val.getValue().equals(value))
			{
				return val;
			}
		}

		return null;
	}
}
