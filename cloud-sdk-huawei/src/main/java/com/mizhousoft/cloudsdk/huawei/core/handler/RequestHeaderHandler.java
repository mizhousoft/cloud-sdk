package com.mizhousoft.cloudsdk.huawei.core.handler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.core.GeneralRequest;
import com.mizhousoft.cloudsdk.huawei.core.annotation.HeaderParam;

/**
 * 处理器
 *
 * @version
 */
public abstract class RequestHeaderHandler
{
	private static Map<String, Map<String, Field>> classFieldMap = new HashMap<>(40);

	/**
	 * 提取查询参数
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	public static Map<String, String> extractHeaderParams(GeneralRequest request) throws CloudSDKException
	{
		Map<String, Field> fields = getClassFields(request.getClass());
		Iterator<Entry<String, Field>> iter = fields.entrySet().iterator();

		Map<String, String> headerParamMap = new HashMap<>(10);

		while (iter.hasNext())
		{
			Entry<String, Field> entry = iter.next();
			Field field = entry.getValue();

			try
			{
				Object object = field.get(request);
				if (null != object)
				{
					headerParamMap.put(entry.getKey(), object.toString());
				}
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				throw new CloudSDKException("Header param extract failed.", e);
			}
		}

		return headerParamMap;
	}

	private synchronized static <T> Map<String, Field> getClassFields(Class<T> clazz)
	{
		String name = clazz.getClass().getName();

		Map<String, Field> fields = classFieldMap.get(name);
		if (null != fields)
		{
			return fields;
		}

		fields = new HashMap<>(5);

		for (Field field : clazz.getDeclaredFields())
		{
			if (field.isAnnotationPresent(HeaderParam.class))
			{
				HeaderParam annotation = field.getAnnotation(HeaderParam.class);

				String value = annotation.value();
				if (!StringUtils.isBlank(value))
				{
					field.setAccessible(true);

					fields.put(value, field);
				}
			}
		}

		classFieldMap.put(name, fields);

		return fields;
	}
}
