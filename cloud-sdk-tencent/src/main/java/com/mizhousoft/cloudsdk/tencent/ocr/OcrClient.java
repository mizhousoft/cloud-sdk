package com.mizhousoft.cloudsdk.tencent.ocr;

import com.mizhousoft.cloudsdk.CloudSDKNewException;
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
	 * @throws CloudSDKNewException
	 */
	TempCredential genTempCredential(long durationSecond) throws CloudSDKNewException;

	/**
	 * 识别
	 * 
	 * @param imageBase64
	 * @return
	 * @throws CloudSDKNewException
	 */
	String imageBase64OCR(String imageBase64) throws CloudSDKNewException;

	/**
	 * 识别
	 * 
	 * @param imageUrl
	 * @return
	 * @throws CloudSDKNewException
	 */
	String imageUrlOCR(String imageUrl) throws CloudSDKNewException;
}
