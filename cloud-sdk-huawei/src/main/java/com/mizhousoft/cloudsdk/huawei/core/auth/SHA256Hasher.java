package com.mizhousoft.cloudsdk.huawei.core.auth;

import com.mizhousoft.commons.crypto.CryptoException;
import com.mizhousoft.commons.crypto.digest.HmacSHA256Digest;
import com.mizhousoft.commons.crypto.digest.SHA256Digest;

public class SHA256Hasher extends Hasher
{
	@Override
	public byte[] hash(byte[] data)
	{
		try
		{
			return SHA256Digest.hash(data);
		}
		catch (CryptoException e)
		{
			throw new RuntimeException("Unable to compute hash while signing request", e);
		}
	}

	@Override
	public byte[] hmac(byte[] data, byte[] key)
	{
		try
		{
			return HmacSHA256Digest.hash(key, data);
		}
		catch (CryptoException e)
		{
			throw new RuntimeException("Unable to calculate a request signature: " + e.getMessage(), e);
		}
	}
}