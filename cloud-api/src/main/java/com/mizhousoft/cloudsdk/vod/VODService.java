package com.mizhousoft.cloudsdk.vod;

import java.io.File;
import java.util.List;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.vod.event.VodEvent;

/**
 * 视频点播服务
 *
 * @version
 */
public interface VODService
{
	/**
	 * 上传视频
	 * 
	 * @param mediaFile
	 * @param coverFile
	 * @throws CloudSDKException
	 */
	String uploadVideo(File mediaFile, File coverFile) throws CloudSDKException;

	/**
	 * 截图封面
	 * 
	 * @param fileId
	 * @param screenshotTmplId
	 * @param timePosition
	 * @throws CloudSDKException
	 */
	String snapshotCover(String fileId, long screenshotTmplId, float timePosition) throws CloudSDKException;

	/**
	 * 转码
	 * 
	 * @param fileId
	 * @param transcodeTmplId
	 * @param screenshotTmplId
	 * @param timePosition
	 * @return
	 * @throws CloudSDKException
	 */
	String transcode(String fileId, long transcodeTmplId, long screenshotTmplId, float timePosition) throws CloudSDKException;

	/**
	 * 修改封面
	 * 
	 * @param fileId
	 * @param base64Data
	 * @return
	 * @throws CloudSDKException
	 */
	String modifyCover(String fileId, String base64Data) throws CloudSDKException;

	/**
	 * 删除视频
	 * 
	 * @param fileId
	 * @throws CloudSDKException
	 */
	void deleteVideo(String fileId) throws CloudSDKException;

	/**
	 * 获取视频元数据
	 * 
	 * @param fileId
	 * @return
	 * @throws CloudSDKException
	 */
	MediaMeta getVideoMeta(String fileId) throws CloudSDKException;

	/**
	 * 拉取事件
	 * 
	 * @throws CloudSDKException
	 */
	List<VodEvent> pullEvents() throws CloudSDKException;

	/**
	 * 确认事件
	 * 
	 * @param eventIds
	 * @throws CloudSDKException
	 */
	void confirmEvents(String[] eventIds) throws CloudSDKException;
}
