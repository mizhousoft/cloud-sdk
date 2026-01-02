package com.mizhousoft.cloudsdk.tencent.sms.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 套餐包信息统计
 *
 * @version
 */
public class SmsPackagesStatistics
{
	/**
	 * 套餐包创建时间，UNIX 时间戳（单位：秒）。
	 * 示例值：1733040000
	 */
	@JsonProperty("PackageCreateTime")
	private long packageCreateTime;

	/**
	 * 套餐包生效时间，UNIX 时间戳（单位：秒）。
	 * 示例值：1733040000
	 */
	@JsonProperty("PackageEffectiveTime")
	private Long packageEffectiveTime;

	/**
	 * 套餐包过期时间，UNIX 时间戳（单位：秒）。
	 * 示例值：1796112000
	 */
	@JsonProperty("PackageExpiredTime")
	private Long packageExpiredTime;

	/**
	 * 套餐包条数。
	 */
	@JsonProperty("PackageAmount")
	private Long packageAmount;

	/**
	 * 套餐包类别，0表示赠送套餐包，1表示购买套餐包。
	 */
	@JsonProperty("PackageType")
	private Long packageType;

	/**
	 * 套餐包 ID。
	 */
	@JsonProperty("PackageId")
	private Long packageId;

	/**
	 * 当前使用量。
	 */
	@JsonProperty("CurrentUsage")
	private Long currentUsage;

	/**
	 * 获取packageCreateTime
	 * 
	 * @return
	 */
	public long getPackageCreateTime()
	{
		return packageCreateTime;
	}

	/**
	 * 设置packageCreateTime
	 * 
	 * @param packageCreateTime
	 */
	public void setPackageCreateTime(long packageCreateTime)
	{
		this.packageCreateTime = packageCreateTime;
	}

	/**
	 * 获取packageEffectiveTime
	 * 
	 * @return
	 */
	public Long getPackageEffectiveTime()
	{
		return packageEffectiveTime;
	}

	/**
	 * 设置packageEffectiveTime
	 * 
	 * @param packageEffectiveTime
	 */
	public void setPackageEffectiveTime(Long packageEffectiveTime)
	{
		this.packageEffectiveTime = packageEffectiveTime;
	}

	/**
	 * 获取packageExpiredTime
	 * 
	 * @return
	 */
	public Long getPackageExpiredTime()
	{
		return packageExpiredTime;
	}

	/**
	 * 设置packageExpiredTime
	 * 
	 * @param packageExpiredTime
	 */
	public void setPackageExpiredTime(Long packageExpiredTime)
	{
		this.packageExpiredTime = packageExpiredTime;
	}

	/**
	 * 获取packageAmount
	 * 
	 * @return
	 */
	public Long getPackageAmount()
	{
		return packageAmount;
	}

	/**
	 * 设置packageAmount
	 * 
	 * @param packageAmount
	 */
	public void setPackageAmount(Long packageAmount)
	{
		this.packageAmount = packageAmount;
	}

	/**
	 * 获取packageType
	 * 
	 * @return
	 */
	public Long getPackageType()
	{
		return packageType;
	}

	/**
	 * 设置packageType
	 * 
	 * @param packageType
	 */
	public void setPackageType(Long packageType)
	{
		this.packageType = packageType;
	}

	/**
	 * 获取packageId
	 * 
	 * @return
	 */
	public Long getPackageId()
	{
		return packageId;
	}

	/**
	 * 设置packageId
	 * 
	 * @param packageId
	 */
	public void setPackageId(Long packageId)
	{
		this.packageId = packageId;
	}

	/**
	 * 获取currentUsage
	 * 
	 * @return
	 */
	public Long getCurrentUsage()
	{
		return currentUsage;
	}

	/**
	 * 设置currentUsage
	 * 
	 * @param currentUsage
	 */
	public void setCurrentUsage(Long currentUsage)
	{
		this.currentUsage = currentUsage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"packageCreateTime\":\"");
		builder.append(packageCreateTime);
		builder.append("\", \"");
		if (packageEffectiveTime != null)
		{
			builder.append("packageEffectiveTime\":\"");
			builder.append(packageEffectiveTime);
			builder.append("\", \"");
		}
		if (packageExpiredTime != null)
		{
			builder.append("packageExpiredTime\":\"");
			builder.append(packageExpiredTime);
			builder.append("\", \"");
		}
		if (packageAmount != null)
		{
			builder.append("packageAmount\":\"");
			builder.append(packageAmount);
			builder.append("\", \"");
		}
		if (packageType != null)
		{
			builder.append("packageType\":\"");
			builder.append(packageType);
			builder.append("\", \"");
		}
		if (packageId != null)
		{
			builder.append("packageId\":\"");
			builder.append(packageId);
			builder.append("\", \"");
		}
		if (currentUsage != null)
		{
			builder.append("currentUsage\":\"");
			builder.append(currentUsage);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
