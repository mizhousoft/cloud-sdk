package com.mizhousoft.tencent.ocr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.TempCredential;
import com.mizhousoft.cloudsdk.ocr.OCRProfile;
import com.mizhousoft.cloudsdk.ocr.OCRService;

/**
 * TencentOCRServiceImpl Test
 *
 * @version
 */
public class TestTencentOCRServiceImpl
{
	private OCRService ocrService;

	@Before
	public void before() throws CloudSDKException
	{
		OCRProfile profile = new OCRProfile();
		profile.setAccessKey("");
		profile.setSecretKey("");
		profile.setRegion("ap-guangzhou");

		TencentOCRServiceImpl service = new TencentOCRServiceImpl();
		service.init(profile);

		this.ocrService = service;
	}

	@Test
	public void getTempCredential()
	{
		try
		{
			TempCredential tempCredential = ocrService.getTempCredential(30);

			Assert.assertNotNull(tempCredential);
		}
		catch (CloudSDKException e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
