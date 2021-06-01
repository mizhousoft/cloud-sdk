package com.mizhousoft.cloudsdk.vod.event;

import com.mizhousoft.cloudsdk.vod.MediaMeta;

/**
 * 文件上传事件
 *
 * @version
 */
public class VodNewFileUploadEvent extends VodEvent
{
	// 元数据
	private final MediaMeta mediaMeta;

	/**
	 * 构造函数
	 *
	 * @param eventId
	 * @param mediaMeta
	 */
	public VodNewFileUploadEvent(String eventId, MediaMeta mediaMeta)
	{
		super(eventId);
		this.mediaMeta = mediaMeta;
	}

	/**
	 * 获取mediaMeta
	 * 
	 * @return
	 */
	public MediaMeta getMediaMeta()
	{
		return mediaMeta;
	}
}
