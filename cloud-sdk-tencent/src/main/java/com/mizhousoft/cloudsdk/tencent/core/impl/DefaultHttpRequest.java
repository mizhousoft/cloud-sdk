package com.mizhousoft.cloudsdk.tencent.core.impl;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
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
import com.mizhousoft.cloudsdk.tencent.auth.Sign;
import com.mizhousoft.cloudsdk.tencent.core.HttpRequest;
import com.mizhousoft.commons.json.JSONException;
import com.mizhousoft.commons.json.JSONUtils;

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
	 * 请求协议
	 */
	private String protocol;

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
	private final Map<String, String> headers = new HashMap<>();

	/**
	 * url
	 */
	private URL url;

	/**
	 * 查询参数字符串
	 */
	private String queryParamsString;

	/**
	 * 是否跳过请求签名
	 */
	private boolean skipRequestSign = false;

	/**
	 * 是否不签名Payload
	 */
	private boolean unsignedPayload = false;

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
		 * 协议
		 * 
		 * @param protocol
		 * @return
		 */
		public Builder protocol(String protocol)
		{
			impl.protocol = protocol;

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
		 * 设置Header
		 * 
		 * @param headerMap
		 * @return
		 */
		public Builder headers(Map<String, String> headerMap)
		{
			headerMap.forEach((key, value) -> header(key, value));

			return this;
		}

		/**
		 * 设置Header
		 * 
		 * @param key
		 * @param value
		 * @return
		 */
		public Builder header(String key, String value)
		{
			impl.headers.put(key, value);

			return this;
		}

		/**
		 * 是否跳过请求签名
		 * 
		 * @return
		 */
		public Builder skipRequestSign()
		{
			impl.skipRequestSign = true;

			return this;
		}

		/**
		 * 是否不签名
		 * 
		 * @return
		 */
		public Builder unsignedPayload()
		{
			impl.unsignedPayload = true;

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
						sb.append(String.format("%s=%s&", key, Sign.urlEncode(value)));
					}
				});
			});
			impl.queryParamsString = sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : sb.toString();

			try
			{
				if (!StringUtils.isEmpty(impl.queryParamsString))
				{
					impl.url = URI.create(impl.protocol + impl.endpoint + impl.canonicalPath + "?" + impl.queryParamsString).toURL();
				}
				else
				{
					impl.url = URI.create(impl.protocol + impl.endpoint + impl.canonicalPath).toURL();
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
	 * 获取protocol
	 * 
	 * @return
	 */
	public String getProtocol()
	{
		return protocol;
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
	public Map<String, String> getHeaders()
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
	public String getHeader(String name)
	{
		return headers.get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSkipRequestSign()
	{
		return skipRequestSign;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnsignedPayload()
	{
		return unsignedPayload;
	}
}
