package com.mizhousoft.cloudsdk.vod.event;

/**
 * 状态变更事件
 *
 * @version
 */
public class VodStateChangedEvent extends VodEvent
{
	// 文件ID
	private final String fileId;

	// 是否完成
	private final boolean completed;

	/**
	 * 构造函数
	 *
	 * @param eventId
	 * @param fileId
	 * @param completed
	 */
	public VodStateChangedEvent(String eventId, String fileId, boolean completed)
	{
		super(eventId);
		this.fileId = fileId;
		this.completed = completed;
	}

	/**
	 * 获取fileId
	 * 
	 * @return
	 */
	public String getFileId()
	{
		return fileId;
	}

	/**
	 * 获取completed
	 * 
	 * @return
	 */
	public boolean isCompleted()
	{
		return completed;
	}
}
