package com.mizhousoft.cloudsdk;

/**
 * 云供应商
 *
 * @version
 */
public enum CloudProvider
{
    // 腾讯云
	TENCENT("tencent"),
    // 阿里云
	ALIYUN("aliyun");

	/**
	 * 构造函数
	 *
	 * @param val
	 */
	private CloudProvider(String val)
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
	 * @param data
	 * @return
	 */
	public static CloudProvider get(String data)
	{
		CloudProvider[] values = CloudProvider.values();
		for (CloudProvider value : values)
		{
			if (value.getValue().equals(data))
			{
				return value;
			}
		}

		return null;
	}
}
