package com.mizhousoft.cloudsdk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;

/**
 * 文件摘要工具类
 *
 * @version
 */
public abstract class FileDigestUtils
{
	public static String md5AsBase64(File file) throws CloudSDKException
	{
		try (InputStream istream = new FileInputStream(file))
		{
			byte[] binaryData = DigestUtils.md5(IOUtils.toByteArray(istream));
			String md5 = Base64.encodeBase64String(binaryData);

			return md5;
		}
		catch (IOException e)
		{
			throw new CloudSDKException("Read file failed.", e);
		}
	}
}
