package com.mizhousoft.cloudsdk.tencent.ocr.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mizhousoft.cloudsdk.tencent.core.GeneralResponse;

/**
 * VIN OCR响应
 *
 * @version
 */
public class VinOCRResponse extends GeneralResponse
{
	/**
	 * 检测到的车辆 VIN 码。
	 */
	@JsonProperty("Vin")
	private String vin;

	/**
	 * 获取vin
	 * 
	 * @return
	 */
	public String getVin()
	{
		return vin;
	}

	/**
	 * 设置vin
	 * 
	 * @param vin
	 */
	public void setVin(String vin)
	{
		this.vin = vin;
	}
}
