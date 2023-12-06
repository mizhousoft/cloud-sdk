package com.mizhousoft.aliyun.boot.oss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mizhousoft.aliyun.boot.oss.properties.OSSProfileListProperties;

/**
 * OSSProfileProperties Test
 *
 * @version
 */
@SpringBootTest(classes = DemoApplication.class)
public class OSSProfilePropertiesTest
{
	@Autowired
	private OSSProfileListProperties listProperties;

	@Test
	public void test()
	{
		Assertions.assertEquals(1, listProperties.getList().size());
	}
}
