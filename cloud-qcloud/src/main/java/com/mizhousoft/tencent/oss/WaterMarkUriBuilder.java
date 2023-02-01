package com.mizhousoft.tencent.oss;

import org.apache.commons.codec.binary.Base64;

import com.mizhousoft.cloudsdk.oss.WaterMarkParams;
import com.mizhousoft.commons.lang.CharEncoding;

/**
 * 水印构建器
 *
 * @version
 */
public abstract class WaterMarkUriBuilder
{
	/**
	 * 构建
	 * 
	 * 参考：watermark/2/text/MTg5MDI4NDM0Mzfmn6XnnIs=/fill/I0ZGRTVERA==/fontsize/250/dissolve/30/gravity/center/dx/520/dy/520/batch/1/degree/45
	 * 
	 * @param params
	 * @return
	 */
	public static String build(WaterMarkParams params)
	{
		StringBuilder buffer = new StringBuilder(100);

		buffer.append("watermark/2/text/");
		buffer.append(Base64.encodeBase64URLSafeString(params.getText().getBytes(CharEncoding.UTF8)));

		if (null != params.getFill())
		{
			buffer.append("/fill/");
			buffer.append(Base64.encodeBase64URLSafeString(params.getFill().getBytes(CharEncoding.UTF8)));
		}
		if (0 != params.getFontSize())
		{
			buffer.append("/fontsize/");
			buffer.append(params.getFontSize());
		}
		if (0 != params.getDissolve())
		{
			buffer.append("/dissolve/");
			buffer.append(params.getDissolve());
		}
		if (null != params.getGravity())
		{
			buffer.append("/gravity/");
			buffer.append(params.getGravity());
		}
		if (0 != params.getDx())
		{
			buffer.append("/dx/");
			buffer.append(params.getDx());
		}
		if (0 != params.getDy())
		{
			buffer.append("/dy/");
			buffer.append(params.getDy());
		}
		if (1 == params.getBatch())
		{
			buffer.append("/batch/1");

			if (0 != params.getDegree())
			{
				buffer.append("/degree/");
				buffer.append(params.getDegree());
			}
		}
		if (0 != params.getShadow())
		{
			buffer.append("/shadow/");
			buffer.append(params.getShadow());
		}

		return buffer.toString();
	}
}
