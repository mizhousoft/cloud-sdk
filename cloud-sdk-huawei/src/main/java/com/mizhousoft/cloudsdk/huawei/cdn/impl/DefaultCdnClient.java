package com.mizhousoft.cloudsdk.huawei.cdn.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.cdn.CdnClient;
import com.mizhousoft.cloudsdk.huawei.cdn.request.CreateRefreshTasksRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTaskDetailsRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTasksRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.response.CDNResponse;
import com.mizhousoft.cloudsdk.huawei.cdn.response.CreateRefreshTasksResponse;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTaskDetailsResponse;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTasksResponse;
import com.mizhousoft.cloudsdk.huawei.core.auth.AKSKSigner;
import com.mizhousoft.cloudsdk.huawei.core.auth.HeaderConstants;
import com.mizhousoft.cloudsdk.huawei.core.auth.ICredential;
import com.mizhousoft.cloudsdk.huawei.core.handler.QueryRequestHandler;
import com.mizhousoft.cloudsdk.huawei.core.http.MediaType;
import com.mizhousoft.cloudsdk.huawei.core.impl.DefaultHttpRequest;

import kong.unirest.core.HttpMethod;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.HttpStatus;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;

/**
 * CDN客户端
 *
 * @version
 */
public class DefaultCdnClient implements CdnClient
{
	private static final String ENDPOINT = "https://cdn.myhuaweicloud.com";

	/**
	 * ICredential
	 */
	private ICredential credential;

	/**
	 * 构造函数
	 *
	 * @param credential
	 */
	public DefaultCdnClient(ICredential credential)
	{
		super();
		this.credential = credential;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateRefreshTasksResponse createRefreshTasks(CreateRefreshTasksRequest request) throws CloudSDKException
	{
		return createRefreshTasks(null, request);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateRefreshTasksResponse createRefreshTasks(String enterpriseProjectId, CreateRefreshTasksRequest request)
	        throws CloudSDKException
	{
		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("CreateRefreshTasks")
		        .endpoint(ENDPOINT)
		        .path("/v1.0/cdn/content/refresh-tasks")
		        .httpMethod(HttpMethod.POST)
		        .contentType(MediaType.APPLICATION_JSON)
		        .queryString("enterprise_project_id", enterpriseProjectId)
		        .bodyAsString(request)
		        .build();

		Map<String, String> authHeaders = AKSKSigner.getInstance().sign(httpRequest, credential);

		CreateRefreshTasksResponse response = executeRequest(httpRequest, authHeaders, CreateRefreshTasksResponse.class);

		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShowHistoryTasksResponse showHistoryTasks(ShowHistoryTasksRequest request) throws CloudSDKException
	{
		Map<String, String> queryParamsMap = QueryRequestHandler.extractQueryParams(request);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("ShowHistoryTasks")
		        .endpoint(ENDPOINT)
		        .path("/v1.0/cdn/historytasks")
		        .httpMethod(HttpMethod.GET)
		        .contentType(MediaType.APPLICATION_JSON)
		        .queryString(queryParamsMap)
		        .build();

		Map<String, String> authHeaders = AKSKSigner.getInstance().sign(httpRequest, credential);

		ShowHistoryTasksResponse response = executeRequest(httpRequest, authHeaders, ShowHistoryTasksResponse.class);

		return response;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShowHistoryTaskDetailsResponse showHistoryTaskDetails(ShowHistoryTaskDetailsRequest request) throws CloudSDKException
	{
		Map<String, String> queryParamsMap = QueryRequestHandler.extractQueryParams(request);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("ShowHistoryTaskDetails")
		        .endpoint(ENDPOINT)
		        .path("/v1.0/cdn/historytasks/{taskId}/detail")
		        .httpMethod(HttpMethod.GET)
		        .contentType(MediaType.APPLICATION_JSON)
		        .routeParam("taskId", request.getHistoryTasksId())
		        .queryString(queryParamsMap)
		        .build();

		Map<String, String> authHeaders = AKSKSigner.getInstance().sign(httpRequest, credential);

		ShowHistoryTaskDetailsResponse response = executeRequest(httpRequest, authHeaders, ShowHistoryTaskDetailsResponse.class);

		return response;
	}

	/**
	 * 執行请求
	 * 
	 * @param <T>
	 * @param request
	 * @param headers
	 * @param responseClass
	 * @return
	 * @throws CloudSDKException
	 */
	private <T extends CDNResponse> T executeRequest(DefaultHttpRequest request, Map<String, String> headers, Class<T> responseClass)
	        throws CloudSDKException
	{
		if (null != request.getContentType())
		{
			headers.put(HeaderConstants.CONTENT_TYPE, request.getContentType());
		}

		try
		{
			HttpResponse<T> response = null;

			if (HttpMethod.POST.equals(request.getHttpMethod()))
			{
				response = Unirest.post(request.getUrl().toString()).headers(headers).body(request.getStringBody()).asObject(responseClass);
			}
			else
			{
				response = Unirest.get(request.getUrl().toString()).headers(headers).asObject(responseClass);
			}

			if (response.getStatus() == HttpStatus.OK)
			{
				T respBody = response.getBody();

				respBody.setHttpStatusCode(response.getStatus());
				respBody.setxRequestId(response.getHeaders().getFirst(HeaderConstants.X_REQUEST_ID));

				return respBody;
			}
			else
			{
				throw new CloudSDKException("Request failed, status is " + response.getStatus() + "，response body: " + response.getBody());
			}
		}
		catch (UnirestException e)
		{
			throw new CloudSDKException("HTTP request execution failed", e);
		}
	}

}
