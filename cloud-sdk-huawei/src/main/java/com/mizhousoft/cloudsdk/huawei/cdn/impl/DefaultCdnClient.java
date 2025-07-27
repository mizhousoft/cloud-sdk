package com.mizhousoft.cloudsdk.huawei.cdn.impl;

import java.util.Map;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.huawei.cdn.CdnClient;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTaskDetailsRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.request.ShowHistoryTasksRequest;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTaskDetailsResponse;
import com.mizhousoft.cloudsdk.huawei.cdn.response.ShowHistoryTasksResponse;
import com.mizhousoft.cloudsdk.huawei.core.auth.AKSKSigner;
import com.mizhousoft.cloudsdk.huawei.core.auth.ICredential;
import com.mizhousoft.cloudsdk.huawei.core.handler.QueryRequestHandler;
import com.mizhousoft.cloudsdk.huawei.core.http.HeaderConstants;
import com.mizhousoft.cloudsdk.huawei.core.http.MediaType;
import com.mizhousoft.cloudsdk.huawei.core.impl.DefaultHttpRequest;

import kong.unirest.core.HttpMethod;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.HttpStatus;
import kong.unirest.core.Unirest;

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
	public ShowHistoryTasksResponse showHistoryTasks(ShowHistoryTasksRequest request) throws CloudSDKException
	{
		Map<String, String> queryParamsMap = QueryRequestHandler.extractQueryParams(request);

		DefaultHttpRequest httpRequest = DefaultHttpRequest.builder()
		        .name("ShowHistoryTasks")
		        .endpoint(ENDPOINT)
		        .path("/v1.0/cdn/historytasks")
		        .httpMethod(HttpMethod.GET)
		        .queryString(queryParamsMap)
		        .build();

		Map<String, String> headers = AKSKSigner.getInstance().sign(httpRequest, credential);
		headers.put(HeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		HttpResponse<ShowHistoryTasksResponse> response = Unirest.get(httpRequest.getUrl().toString())
		        .headers(headers)
		        .asObject(ShowHistoryTasksResponse.class);

		if (response.getStatus() == HttpStatus.OK)
		{
			return response.getBody();
		}
		else
		{
			throw new CloudSDKException("Request failed, status is " + response.getStatus() + "，response body: " + response.getBody());
		}
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
		        .routeParam("taskId", request.getHistoryTasksId())
		        .queryString(queryParamsMap)
		        .build();

		Map<String, String> headers = AKSKSigner.getInstance().sign(httpRequest, credential);
		headers.put(HeaderConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON);

		HttpResponse<ShowHistoryTaskDetailsResponse> response = Unirest.get(httpRequest.getUrl().toString())
		        .headers(headers)
		        .asObject(ShowHistoryTaskDetailsResponse.class);

		if (response.getStatus() == HttpStatus.OK)
		{
			return response.getBody();
		}
		else
		{
			throw new CloudSDKException("Request failed, status is " + response.getStatus() + "，response body: " + response.getBody());
		}
	}

}
