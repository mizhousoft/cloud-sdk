package com.mizhousoft.cloudsdk.huawei.cdn;

/**
 * 刷新类型
 * 
 * @version
 */
public enum RefreshTypeEnum
{
    // 文件
	FILE("file"),
    // 目录
	DIRECTORY("directory");

	/**
	 * 构造函数
	 * 
	 * @param value
	 */
	RefreshTypeEnum(String value)
	{
		this.value = value;
	}

	// 值
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
	 * 判断是否自己
	 * 
	 * @param val
	 * @return
	 */
	public boolean isSelf(String val)
	{
		return this.getValue().equalsIgnoreCase(val);
	}

	/**
	 * 获取LogLevel
	 * 
	 * @param value
	 * @return
	 */
	public static RefreshTypeEnum get(String value)
	{
		RefreshTypeEnum[] enumValues = RefreshTypeEnum.values();
		for (RefreshTypeEnum enumValue : enumValues)
		{
			if (enumValue.isSelf(value))
			{
				return enumValue;
			}
		}

		return null;
	}
}
