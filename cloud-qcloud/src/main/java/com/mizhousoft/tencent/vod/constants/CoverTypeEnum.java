package com.mizhousoft.tencent.vod.constants;

/**
 * 封面类型枚举
 *
 * @version
 */
public enum CoverTypeEnum
{
    // JPG
	JPG("jpg"),
    // JPEG
	JPEG("jpeg"),
    // PNG
	PNG("png");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private CoverTypeEnum(String val)
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
	public static CoverTypeEnum get(String value)
	{
		CoverTypeEnum[] values = CoverTypeEnum.values();
		for (CoverTypeEnum val : values)
		{
			if (val.getValue().equals(value))
			{
				return val;
			}
		}

		return null;
	}
}
