package com.mizhousoft.tencent.vod.constants;

/**
 * 视频编码格式枚举
 *
 * @version
 */
public enum MediaCodecEnum
{
    // mpeg4
	MPEG4("mpeg4"),
    // h264
	H264("h264");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private MediaCodecEnum(String val)
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
	public static MediaCodecEnum get(String value)
	{
		MediaCodecEnum[] values = MediaCodecEnum.values();
		for (MediaCodecEnum val : values)
		{
			if (val.getValue().equals(value))
			{
				return val;
			}
		}

		return null;
	}
}
