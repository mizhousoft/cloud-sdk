package com.mizhousoft.cloudsdk.vod.event;

/**
 * 事件
 *
 * @version
 */
public class VodEvent
{
	// 事件ID
	protected final String eventId;

	/**
	 * 构造函数
	 *
	 * @param eventId
	 */
	public VodEvent(String eventId)
	{
		super();
		this.eventId = eventId;
	}

	/**
	 * 获取eventId
	 * 
	 * @return
	 */
	public String getEventId()
	{
		return eventId;
	}
}
