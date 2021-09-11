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
}
