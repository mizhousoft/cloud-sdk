package com.mizhousoft.cloudsdk.vod.event;

/**
 * 文件删除事件
 *
 * @version
 */
public class VodFileDeletedEvent extends VodEvent
{
	// 文件ID
	private final String[] fileIds;

	/**
	 * 构造函数
	 *
	 * @param eventId
	 * @param fileIds
	 */
	public VodFileDeletedEvent(String eventId, String[] fileIds)
	{
		super(eventId);
		this.fileIds = fileIds;
	}

	/**
	 * 获取fileIds
	 * 
	 * @return
	 */
	public String[] getFileIds()
	{
		return fileIds;
	}
}
