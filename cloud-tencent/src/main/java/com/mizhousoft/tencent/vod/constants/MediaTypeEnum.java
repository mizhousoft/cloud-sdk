package com.mizhousoft.tencent.vod.constants;

/**
 * 视频类型枚举
 *
 * @version
 */
public enum MediaTypeEnum
{
    // MP4
	MP4("mp4"),
    // FLV
	FLV("flv"),
    // AVI
	AVI("avi");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private MediaTypeEnum(String val)
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

	public boolean isSelf(String val)
	{
		return (this.value.equals(val));
	}

	/**
	 * 获取状态
	 * 
	 * @param status
	 * @return
	 */
	public static MediaTypeEnum get(String value)
	{
		MediaTypeEnum[] values = MediaTypeEnum.values();
		for (MediaTypeEnum val : values)
		{
			if (val.getValue().equals(value))
			{
				return val;
			}
		}

		return null;
	}
}
