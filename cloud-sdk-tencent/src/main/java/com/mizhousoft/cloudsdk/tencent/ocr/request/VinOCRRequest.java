package com.mizhousoft.cloudsdk.tencent.ocr.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * VIN OCR请求
 *
 * @version
 */
public class VinOCRRequest
{
	/**
	 * 图片的 Base64 值。支持的图片格式：PNG、JPG、JPEG，暂不支持 GIF 格式。支持的图片大小：所下载图片经Base64编码后不超过 10M。图片下载时间不超过 3
	 * 秒。图片的 ImageUrl、ImageBase64 必须提供一个，如果都提供，只使用 ImageUrl。
	 */
	@JsonProperty("ImageBase64")
	private String imageBase64;

	/**
	 * 图片的 Url 地址。支持的图片格式：PNG、JPG、JPEG，暂不支持 GIF 格式。支持的图片大小：所下载图片经 Base64 编码后不超过 10M。图片下载时间不超过 3
	 * 秒。图片存储于腾讯云的 Url 可保障更高的下载速度和稳定性，建议图片存储于腾讯云。非腾讯云存储的 Url 速度和稳定性可能受一定影响。
	 */
	@JsonProperty("ImageUrl")
	private String imageUrl;

	/**
	 * 获取imageBase64
	 * 
	 * @return
	 */
	public String getImageBase64()
	{
		return imageBase64;
	}

	/**
	 * 设置imageBase64
	 * 
	 * @param imageBase64
	 */
	public void setImageBase64(String imageBase64)
	{
		this.imageBase64 = imageBase64;
	}

	/**
	 * 获取imageUrl
	 * 
	 * @return
	 */
	public String getImageUrl()
	{
		return imageUrl;
	}

	/**
	 * 设置imageUrl
	 * 
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}
}
