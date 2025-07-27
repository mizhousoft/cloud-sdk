package com.mizhousoft.cloudsdk.huawei.cdn;

/**
 * 刷新模式
 * 
 * @version
 */
public enum RefreshModeEnum
{
    // 刷新目录下全部资源
	ALL("Info"),
    // 刷新目录下已变更的资源
	DETECT_MODIFY_REFRESH("detect_modify_refresh");

	/**
	 * 构造函数
	 * 
	 * @param value
	 */
	RefreshModeEnum(String value)
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
	public static RefreshModeEnum get(String value)
	{
		RefreshModeEnum[] enumValues = RefreshModeEnum.values();
		for (RefreshModeEnum enumValue : enumValues)
		{
			if (enumValue.isSelf(value))
			{
				return enumValue;
			}
		}

		return null;
	}
}
