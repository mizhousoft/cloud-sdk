package com.mizhousoft.cloudsdk.huawei.core.handler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.core.QueryRequest;
import com.mizhousoft.cloudsdk.huawei.core.annotation.QueryParam;

/**
 * 查询请求处理器
 *
 * @version
 */
public abstract class QueryRequestHandler
{
	/**
	 * 类字段容器，Map<className, Map<AttributeName, Field>>
	 */
	private static Map<String, Map<String, Field>> classFieldMap = new HashMap<>(40);

	/**
	 * 提取查询参数
	 * 
	 * @param request
	 * @return
	 * @throws CloudSDKException
	 */
	public static Map<String, String> extractQueryParams(QueryRequest request) throws CloudSDKException
	{
		Map<String, Field> fields = getClassFields(request.getClass());
		Iterator<Entry<String, Field>> iter = fields.entrySet().iterator();

		Map<String, String> queryParamMap = new HashMap<>(10);

		while (iter.hasNext())
		{
			Entry<String, Field> entry = iter.next();
			Field field = entry.getValue();

			try
			{
				Object object = field.get(request);
				if (null != object)
				{
					queryParamMap.put(entry.getKey(), object.toString());
				}
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				throw new CloudSDKException("Query param extract failed.", e);
			}
		}

		return queryParamMap;
	}

	/**
	 * 获取类属性
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	private synchronized static <T> Map<String, Field> getClassFields(Class<T> clazz)
	{
		String name = clazz.getName();

		Map<String, Field> fields = classFieldMap.get(name);
		if (null != fields)
		{
			return fields;
		}

		fields = new HashMap<>(5);

		for (Field field : clazz.getDeclaredFields())
		{
			if (field.isAnnotationPresent(QueryParam.class))
			{
				QueryParam annotation = field.getAnnotation(QueryParam.class);

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
