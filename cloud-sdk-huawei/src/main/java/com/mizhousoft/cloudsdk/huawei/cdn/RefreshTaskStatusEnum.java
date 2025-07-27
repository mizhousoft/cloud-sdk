package com.mizhousoft.cloudsdk.huawei.cdn;

/**
 * 刷新状态
 *
 * @version
 */
public enum RefreshTaskStatusEnum
{
    // 表示任务处理中
	TASK_INPROCESS("task_inprocess"),
    // 任务完成
	TASK_DONE("task_done");

	/**
	 * 构造函数
	 * 
	 * @param value
	 */
	RefreshTaskStatusEnum(String value)
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