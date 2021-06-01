package com.mizhousoft.cloudsdk.nlp;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 敏感词识别服务
 *
 * @version
 */
public interface SensitiveWordsRecognitionService
{
	/**
	 * 识别
	 * 
	 * @param text
	 * @return
	 * @throws CloudSDKException
	 */
	RecognitionResult recognize(String... text) throws CloudSDKException;
}
