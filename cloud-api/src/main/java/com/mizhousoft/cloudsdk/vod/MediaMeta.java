package com.mizhousoft.cloudsdk.vod;

/**
 * 视频元数据
 *
 * @version
 */
public class MediaMeta
{
	// 文件ID
	private String fileId;

	// 视频名称
	private String name;

	// 视频类型
	private String type;

	// 视频文件大小，单位字节
	private long mediaFileSize;

	// 视频时长，单位秒
	private long duration;

	// 视频URL
	private String mediaUrl;

	// 封面URL
	private String coverUrl;

	// 视频流的编码格式，例如 h264
	private String codec;

	// 宽度
	private long width;

	// 高度
	private long height;

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
	 * 设置fileId
	 * 
	 * @param fileId
	 */
	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	/**
	 * 获取name
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 获取type
	 * 
	 * @return
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * 设置type
	 * 
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * 获取mediaFileSize
	 * 
	 * @return
	 */
	public long getMediaFileSize()
	{
		return mediaFileSize;
	}

	/**
	 * 设置mediaFileSize
	 * 
	 * @param mediaFileSize
	 */
	public void setMediaFileSize(long mediaFileSize)
	{
		this.mediaFileSize = mediaFileSize;
	}

	/**
	 * 获取duration
	 * 
	 * @return
	 */
	public long getDuration()
	{
		return duration;
	}

	/**
	 * 设置duration
	 * 
	 * @param duration
	 */
	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	/**
	 * 获取mediaUrl
	 * 
	 * @return
	 */
	public String getMediaUrl()
	{
		return mediaUrl;
	}

	/**
	 * 设置mediaUrl
	 * 
	 * @param mediaUrl
	 */
	public void setMediaUrl(String mediaUrl)
	{
		this.mediaUrl = mediaUrl;
	}

	/**
	 * 获取coverUrl
	 * 
	 * @return
	 */
	public String getCoverUrl()
	{
		return coverUrl;
	}

	/**
	 * 设置coverUrl
	 * 
	 * @param coverUrl
	 */
	public void setCoverUrl(String coverUrl)
	{
		this.coverUrl = coverUrl;
	}

	/**
	 * 获取codec
	 * 
	 * @return
	 */
	public String getCodec()
	{
		return codec;
	}

	/**
	 * 设置codec
	 * 
	 * @param codec
	 */
	public void setCodec(String codec)
	{
		this.codec = codec;
	}

	/**
	 * 获取width
	 * @return
	 */
	public long getWidth()
	{
		return width;
	}

	/**
	 * 设置width
	 * @param width
	 */
	public void setWidth(long width)
	{
		this.width = width;
	}

	/**
	 * 获取height
	 * @return
	 */
	public long getHeight()
	{
		return height;
	}

	/**
	 * 设置height
	 * @param height
	 */
	public void setHeight(long height)
	{
		this.height = height;
	}
}
