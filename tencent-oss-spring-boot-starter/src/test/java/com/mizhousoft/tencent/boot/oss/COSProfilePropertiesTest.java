package com.mizhousoft.tencent.boot.oss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mizhousoft.tencent.boot.oss.properties.COSProfileListProperties;

/**
 * COSProfileProperties Test
 *
 * @version
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
public class COSProfilePropertiesTest
{
	@Autowired
	private COSProfileListProperties listProperties;

	@Test
	public void test()
	{
		Assertions.assertEquals(1, listProperties.getList().size());
	}
}
