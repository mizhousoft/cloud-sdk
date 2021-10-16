package com.mizhousoft.cloudsdk.ocr;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;

/**
 * OCR服务
 *
 * @version
 */
public interface OCRService
{
	/**
	 * 获取临时密钥
	 * 
	 * @param durationSecond
	 * @return
	 * @throws CloudSDKException
	 */
	TempCredential getTempCredential(long durationSecond) throws CloudSDKException;

	/**
	 * 识别
	 * 
	 * @param imageBase64
	 * @return
	 * @throws CloudSDKException
	 */
	String imageBase64OCR(String imageBase64) throws CloudSDKException;

	/**
	 * 识别
	 * 
	 * @param imageUrl
	 * @return
	 * @throws CloudSDKException
	 */
	String imageUrlOCR(String imageUrl) throws CloudSDKException;
}
