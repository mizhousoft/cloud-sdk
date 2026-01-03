package com.mizhousoft.cloudsdk.tencent.ocr;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;

/**
 * OCR客户端
 *
 * @version
 */
public interface OcrClient
{
	/**
	 * 生成临时凭证
	 * 
	 * @param durationSecond
	 * @return
	 * @throws CloudSDKException
	 */
	TempCredential genTempCredential(long durationSecond) throws CloudSDKException;

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
