package com.mizhousoft.cloudsdk;

/**
 * 区域枚举
 *
 * @version
 */
public enum TencentRegionEnum
{
    // 广州
	GUANGZHOU("ap-guangzhou"),
    // 广州2
	GUANGZHOU2("ap-guangzhou-2"),
    // 上海
	SHANGHAI("ap-shanghai");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private TencentRegionEnum(String val)
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
	public static TencentRegionEnum get(String value)
	{
		TencentRegionEnum[] values = TencentRegionEnum.values();
		for (TencentRegionEnum val : values)
		{
			if (val.getValue().equals(value))
			{
				return val;
			}
		}

		return null;
	}
}
