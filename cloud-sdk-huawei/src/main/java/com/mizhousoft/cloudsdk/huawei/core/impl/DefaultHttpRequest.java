package com.mizhousoft.cloudsdk.huawei.core.impl;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.core.HttpRequest;
import com.mizhousoft.cloudsdk.huawei.core.auth.SignUtils;
import com.mizhousoft.commons.json.JSONException;
import com.mizhousoft.commons.json.JSONUtils;
import com.mizhousoft.commons.lang.CollectionUtils;

import kong.unirest.core.HttpMethod;

/**
 * 请求
 *
 * @version
 */
public class DefaultHttpRequest implements HttpRequest
{
	/**
	 * 名称
	 */
	private String name;

	/**
	 * endpoint
	 */
	private String endpoint;

	/**
	 * 原始请求路径
	 */
	private String rawPath;

	/**
	 * 规范请求路径
	 */
	private String canonicalPath;

	/**
	 * HttpMethod
	 */
	private HttpMethod httpMethod;

	/**
	 * contentType
	 */
	private String contentType;

	/**
	 * 请求Body字符串
	 */
	private String stringBody;

	/**
	 * 数据流
	 */
	private InputStream streamBody;

	/**
	 * 查询参数
	 */
	private final Map<String, List<String>> queryParams = new LinkedHashMap<>();

	/**
	 * 路径参数
	 */
	private final Map<String, Object> pathParams = new LinkedHashMap<>();

	/**
	 * Header
	 */
	private final Map<String, List<String>> headers = new HashMap<>();

	/**
	 * url
	 */
	private URL url;

	/**
	 * 查询参数字符串
	 */
	private String queryParamsString;

	/**
	 * 创建Builder
	 * 
	 * @return
	 */
	public static Builder builder()
	{
		return new Builder();
	}

	/**
	 * Builder
	 *
	 * @version AbstractHttpRequest
	 */
	public static class Builder
	{
		protected DefaultHttpRequest impl;

		/**
		 * 构造函数
		 *
		 */
		public Builder()
		{
			super();

			this.impl = new DefaultHttpRequest();
		}

		/**
		 * 设置name
		 * 
		 * @param name
		 * @return
		 */
		public Builder name(String name)
		{
			impl.name = name;

			return this;
		}

		/**
		 * 设置endpoint
		 * 
		 * @param endpoint
		 * @return
		 */
		public Builder endpoint(String endpoint)
		{
			impl.endpoint = endpoint;

			return this;
		}

		/**
		 * 设置path
		 * 
		 * @param path
		 * @return
		 */
		public Builder path(String path)
		{
			impl.rawPath = path;

			return this;
		}

		/**
		 * 设置method
		 * 
		 * @param method
		 * @return
		 */
		public Builder httpMethod(HttpMethod httpMethod)
		{
			impl.httpMethod = httpMethod;

			return this;
		}

		/**
		 * 设置contentType
		 * 
		 * @param contentType
		 * @return
		 */
		public Builder contentType(String contentType)
		{
			impl.contentType = contentType;

			return this;
		}

		/**
		 * 设置字符串body
		 * 
		 * @param stringBody
		 * @return
		 */
		public Builder stringBody(String stringBody)
		{
			impl.stringBody = stringBody;

			return this;
		}

		/**
		 * 设置字符串body
		 * 
		 * @param object
		 * @return
		 * @throws CloudSDKException
		 */
		public Builder bodyAsString(Object object) throws CloudSDKException
		{
			try
			{
				impl.stringBody = JSONUtils.toJSONString(object);

				return this;
			}
			catch (JSONException e)
			{
				throw new CloudSDKException("Object serialize to a string failed.", e);
			}
		}

		/**
		 * 设置流body
		 * 
		 * @param streamBody
		 * @return
		 */
		public Builder streamBody(InputStream streamBody)
		{
			impl.streamBody = streamBody;

			return this;
		}

		/**
		 * 设置路径参数
		 * 
		 * @param name
		 * @param value
		 * @return
		 */
		public Builder routeParam(String name, String value)
		{
			if (null != value)
			{
				impl.pathParams.put(name, value);
			}

			return this;
		}

		/**
		 * 设置查询参数
		 * 
		 * @param name
		 * @param value
		 * @return
		 */
		public Builder queryString(String name, Object value)
		{
			if (null != value)
			{
				List<String> list = impl.queryParams.computeIfAbsent(name, o -> new ArrayList<String>(1));
				list.add(String.valueOf(value));
			}

			return this;
		}

		/**
		 * 设置查询参数
		 * 
		 * @param queryParamsMap
		 * @return
		 */
		public Builder queryString(Map<String, String> queryParamsMap)
		{
			if (null != queryParamsMap)
			{
				queryParamsMap.forEach((key, value) -> queryString(key, value));
			}

			return this;
		}

		/**
		 * 生成结果
		 * 
		 * @return
		 */
		public DefaultHttpRequest build()
		{
			impl.canonicalPath = Objects.isNull(impl.rawPath) ? "" : impl.rawPath;

			impl.pathParams
			        .forEach((key, value) -> impl.canonicalPath = impl.canonicalPath.replace(String.format("{%s}", key), value.toString()));

			StringBuilder sb = new StringBuilder();
			impl.queryParams.forEach((key, values) -> {
				if (values.size() == 0)
				{
					sb.append(String.format("%s=&", key));
				}
				values.forEach(value -> {
					if (value.isEmpty())
					{
						sb.append(String.format("%s=&", key));
					}
					else
					{
						sb.append(String.format("%s=%s&", key, SignUtils.urlEncode(value, false)));
					}
				});
			});
			impl.queryParamsString = sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();

			try
			{
				if (!StringUtils.isEmpty(impl.queryParamsString))
				{
					impl.url = new URL(impl.endpoint + impl.canonicalPath + "?" + impl.queryParamsString);
				}
				else
				{
					impl.url = new URL(impl.endpoint + impl.canonicalPath);
				}
			}
			catch (MalformedURLException e)
			{
				throw new RuntimeException(e);
			}

			return impl;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint()
	{
		return endpoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRawPath()
	{
		return rawPath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCanonicalPath()
	{
		return canonicalPath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HttpMethod getHttpMethod()
	{
		return httpMethod;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL getUrl()
	{
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStringBody()
	{
		return stringBody;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getStreamBody()
	{
		return streamBody;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getQueryParamsString()
	{
		return queryParamsString;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getQueryParams()
	{
		return Collections.unmodifiableMap(queryParams);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getHeaders()
	{
		return Collections.unmodifiableMap(headers);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean containHeader(String name)
	{
		return headers.containsKey(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getHeader(String name)
	{
		return headers.get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFirstHeader(String name)
	{
		List<String> values = headers.get(name);

		return CollectionUtils.isEmpty(values) ? null : values.get(0);
	}
}
