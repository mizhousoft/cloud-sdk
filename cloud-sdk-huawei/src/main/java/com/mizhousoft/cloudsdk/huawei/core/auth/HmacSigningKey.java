package com.mizhousoft.cloudsdk.huawei.core.auth;

import java.util.Arrays;

/**
 * 签名
 *
 * @version
 */
public class HmacSigningKey implements ISigningKey
{
	private final Hasher hasher;

	private final byte[] key;

	public HmacSigningKey(Hasher hasher, byte[] key)
	{
		this.hasher = hasher;
		this.key = key;
	}

	@Override
	public byte[] sign(byte[] data)
	{
		return hasher.hmac(data, key);
	}

	@Override
	public boolean verify(byte[] signature, byte[] data)
	{
		byte[] hmac = hasher.hmac(data, key);
		return Arrays.equals(signature, hmac);
	}
}