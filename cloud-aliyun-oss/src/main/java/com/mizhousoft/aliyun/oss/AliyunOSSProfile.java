package com.mizhousoft.aliyun.oss;

import com.mizhousoft.cloudsdk.oss.OSSProfile;

/**
 * Profile
 *
 * @version
 */
public class AliyunOSSProfile extends OSSProfile
{
	// endpoint
	private String endpoint;

	// STS Endpoint
	private String stsEndpoint;

	// 角色ARN
	private String roleArn;

	// 用来标识临时访问凭证的名称，建议使用不同的应用程序用户来区分
	private String roleSessionName;

	/**
	 * 获取endpoint
	 * 
	 * @return
	 */
	public String getEndpoint()
	{
		return endpoint;
	}

	/**
	 * 设置endpoint
	 * 
	 * @param endpoint
	 */
	public void setEndpoint(String endpoint)
	{
		this.endpoint = endpoint;
	}

	/**
	 * 获取stsEndpoint
	 * 
	 * @return
	 */
	public String getStsEndpoint()
	{
		return stsEndpoint;
	}

	/**
	 * 设置stsEndpoint
	 * 
	 * @param stsEndpoint
	 */
	public void setStsEndpoint(String stsEndpoint)
	{
		this.stsEndpoint = stsEndpoint;
	}

	/**
	 * 获取roleArn
	 * 
	 * @return
	 */
	public String getRoleArn()
	{
		return roleArn;
	}

	/**
	 * 设置roleArn
	 * 
	 * @param roleArn
	 */
	public void setRoleArn(String roleArn)
	{
		this.roleArn = roleArn;
	}

	/**
	 * 获取roleSessionName
	 * 
	 * @return
	 */
	public String getRoleSessionName()
	{
		return roleSessionName;
	}

	/**
	 * 设置roleSessionName
	 * 
	 * @param roleSessionName
	 */
	public void setRoleSessionName(String roleSessionName)
	{
		this.roleSessionName = roleSessionName;
	}
}
