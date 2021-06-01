package com.mizhousoft.cloudsdk.oss;

import java.util.Date;

/**
 * 对象元数据
 *
 * @version
 */
public class ObjectMetadata
{
	// 对象名称
	String objectName;

	// 内容大小
	private long contentLength;

	// 内容MD5
	private String contentMD5;

	// 最后修改时间
	private Date lastModified;

	// 内容类型
	private String contentType;

	/**
	 * 获取objectName
	 * 
	 * @return
	 */
	public String getObjectName()
	{
		return objectName;
	}

	/**
	 * 设置objectName
	 * 
	 * @param objectName
	 */
	public void setObjectName(String objectName)
	{
		this.objectName = objectName;
	}

	/**
	 * 获取contentLength
	 * 
	 * @return
	 */
	public long getContentLength()
	{
		return contentLength;
	}

	/**
	 * 设置contentLength
	 * 
	 * @param contentLength
	 */
	public void setContentLength(long contentLength)
	{
		this.contentLength = contentLength;
	}

	/**
	 * 获取contentMD5
	 * 
	 * @return
	 */
	public String getContentMD5()
	{
		return contentMD5;
	}

	/**
	 * 设置contentMD5
	 * 
	 * @param contentMD5
	 */
	public void setContentMD5(String contentMD5)
	{
		this.contentMD5 = contentMD5;
	}

	/**
	 * 获取lastModified
	 * 
	 * @return
	 */
	public Date getLastModified()
	{
		return lastModified;
	}

	/**
	 * 设置lastModified
	 * 
	 * @param lastModified
	 */
	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	/**
	 * 获取contentType
	 * 
	 * @return
	 */
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * 设置contentType
	 * 
	 * @param contentType
	 */
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (objectName != null)
		{
			builder.append("objectName\":\"");
			builder.append(objectName);
			builder.append("\", \"");
		}
		builder.append("contentLength\":\"");
		builder.append(contentLength);
		builder.append("\", \"");
		if (contentMD5 != null)
		{
			builder.append("contentMD5\":\"");
			builder.append(contentMD5);
			builder.append("\", \"");
		}
		if (lastModified != null)
		{
			builder.append("lastModified\":\"");
			builder.append(lastModified);
			builder.append("\", \"");
		}
		if (contentType != null)
		{
			builder.append("contentType\":\"");
			builder.append(contentType);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
