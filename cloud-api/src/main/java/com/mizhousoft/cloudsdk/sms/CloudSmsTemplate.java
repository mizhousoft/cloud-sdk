package com.mizhousoft.cloudsdk.sms;

/**
 * 云短信模板
 *
 * @version
 */
public class CloudSmsTemplate
{
	// 应用ID
	private final String appId;

	// 模板编码
	private final String templateCode;

	// 签名名称
	private final String signName;

	// 模板ID
	private final Object templateId;

	/**
	 * 构造函数
	 *
	 * @param appId
	 * @param templateCode
	 * @param signName
	 * @param templateId
	 */
	public CloudSmsTemplate(String appId, String templateCode, String signName, Object templateId)
	{
		super();
		this.appId = appId;
		this.templateCode = templateCode;
		this.signName = signName;
		this.templateId = templateId;
	}

	/**
	 * 获取appId
	 * 
	 * @return
	 */
	public String getAppId()
	{
		return appId;
	}

	/**
	 * 获取templateCode
	 * 
	 * @return
	 */
	public String getTemplateCode()
	{
		return templateCode;
	}

	/**
	 * 获取signName
	 * 
	 * @return
	 */
	public String getSignName()
	{
		return signName;
	}

	/**
	 * 获取templateId
	 * 
	 * @return
	 */
	public Object getTemplateId()
	{
		return templateId;
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
		if (templateCode != null)
		{
			builder.append("templateCode\":\"");
			builder.append(templateCode);
			builder.append("\", \"");
		}
		if (signName != null)
		{
			builder.append("signName\":\"");
			builder.append(signName);
			builder.append("\", \"");
		}
		if (templateId != null)
		{
			builder.append("templateId\":\"");
			builder.append(templateId);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
