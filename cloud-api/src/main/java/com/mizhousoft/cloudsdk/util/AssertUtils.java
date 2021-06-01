package com.mizhousoft.cloudsdk.util;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 工具类
 *
 * @version
 */
public class AssertUtils
{
	public static void notNull(Object object, String message) throws CloudSDKException
	{
		if (object == null)
		{
			throw new CloudSDKException(message);
		}
	}
}
