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
	 * @return
	 * @throws CloudSDKException
	 */
	TempCredential getTempCredential() throws CloudSDKException;
}
