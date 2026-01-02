package com.mizhousoft.cloudsdk.tencent.auth;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * 签名
 *
 * @version
 */
public class Sign
{
	private static final Charset UTF8 = StandardCharsets.UTF_8;

	public static String sha256Hex(byte[] b) throws CloudSDKException
	{
		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new CloudSDKException("SHA-256 is not supported." + e.getMessage());
		}
		byte[] d = md.digest(b);
		return HexUtils.encodeHexString(d, true);
	}

	public static byte[] hmac256(byte[] key, String msg) throws CloudSDKException
	{
		Mac mac;
		try
		{
			mac = Mac.getInstance("HmacSHA256");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new CloudSDKException("HmacSHA256 is not supported." + e.getMessage());
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
		try
		{
			mac.init(secretKeySpec);
		}
		catch (InvalidKeyException e)
		{
			throw new CloudSDKException(e.getClass().getName() + "-" + e.getMessage());
		}
		return mac.doFinal(msg.getBytes(UTF8));
	}

	public static String urlEncode(String value)
	{
		if (value == null)
		{
			return "";
		}

		String encoded = URLEncoder.encode(value, CharEncoding.UTF8);

		return encoded;
	}
}
