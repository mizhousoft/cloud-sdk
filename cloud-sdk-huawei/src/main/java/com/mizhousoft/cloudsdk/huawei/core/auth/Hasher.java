package com.mizhousoft.cloudsdk.huawei.core.auth;

import com.mizhousoft.commons.lang.HexUtils;

/**
 * 签名
 *
 * @version
 */
public abstract class Hasher
{
	public abstract byte[] hash(byte[] data);

	public abstract byte[] hmac(byte[] data, byte[] key);

	public String hashHexString(byte[] data)
	{
		return HexUtils.encodeHexString(hash(data), true);
	}
}
