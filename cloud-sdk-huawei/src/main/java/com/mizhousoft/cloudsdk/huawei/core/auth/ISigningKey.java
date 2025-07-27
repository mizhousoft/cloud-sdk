package com.mizhousoft.cloudsdk.huawei.core.auth;

/**
 * 签名
 *
 * @version
 */
public interface ISigningKey
{
	byte[] sign(byte[] data);

	boolean verify(byte[] signature, byte[] data);
}
