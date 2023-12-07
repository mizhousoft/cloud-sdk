package com.mizhousoft.tencent.vod.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.cloudsdk.CloudSDKException;
import com.mizhousoft.cloudsdk.oss.ObjectStorageService;
import com.mizhousoft.cloudsdk.vod.MediaMeta;
import com.mizhousoft.cloudsdk.vod.VODService;
import com.mizhousoft.cloudsdk.vod.event.VodEvent;
import com.mizhousoft.cloudsdk.vod.event.VodFileDeletedEvent;
import com.mizhousoft.cloudsdk.vod.event.VodNewFileUploadEvent;
import com.mizhousoft.cloudsdk.vod.event.VodStateChangedEvent;
import com.mizhousoft.tencent.oss.COSClientBuilder;
import com.mizhousoft.tencent.oss.COSObjectStorageServiceImpl;
import com.mizhousoft.tencent.oss.COSProfile;
import com.mizhousoft.tencent.vod.constants.CoverTypeEnum;
import com.mizhousoft.tencent.vod.constants.MediaCodecEnum;
import com.mizhousoft.tencent.vod.constants.MediaTypeEnum;
import com.mizhousoft.tencent.vod.profile.VodProfile;
import com.mizhousoft.tencent.vod.validator.VodProfileValidator;
import com.qcloud.cos.COSClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.ApplyUploadRequest;
import com.tencentcloudapi.vod.v20180717.models.ApplyUploadResponse;
import com.tencentcloudapi.vod.v20180717.models.CommitUploadRequest;
import com.tencentcloudapi.vod.v20180717.models.CommitUploadResponse;
import com.tencentcloudapi.vod.v20180717.models.ConfirmEventsRequest;
import com.tencentcloudapi.vod.v20180717.models.CoverBySnapshotTaskInput;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeMediaInfosResponse;
import com.tencentcloudapi.vod.v20180717.models.EventContent;
import com.tencentcloudapi.vod.v20180717.models.FileDeleteTask;
import com.tencentcloudapi.vod.v20180717.models.FileUploadTask;
import com.tencentcloudapi.vod.v20180717.models.MediaBasicInfo;
import com.tencentcloudapi.vod.v20180717.models.MediaInfo;
import com.tencentcloudapi.vod.v20180717.models.MediaMetaData;
import com.tencentcloudapi.vod.v20180717.models.MediaProcessTaskInput;
import com.tencentcloudapi.vod.v20180717.models.MediaTranscodeInfo;
import com.tencentcloudapi.vod.v20180717.models.MediaTranscodeItem;
import com.tencentcloudapi.vod.v20180717.models.MediaVideoStreamItem;
import com.tencentcloudapi.vod.v20180717.models.ModifyMediaInfoRequest;
import com.tencentcloudapi.vod.v20180717.models.ModifyMediaInfoResponse;
import com.tencentcloudapi.vod.v20180717.models.ProcedureTask;
import com.tencentcloudapi.vod.v20180717.models.ProcessMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.ProcessMediaResponse;
import com.tencentcloudapi.vod.v20180717.models.PullEventsRequest;
import com.tencentcloudapi.vod.v20180717.models.PullEventsResponse;
import com.tencentcloudapi.vod.v20180717.models.TranscodeTaskInput;

/**
 * 腾讯云视频点播服务
 *
 * @version
 */
public class QCloudVODServiceImpl implements VODService
{
	private static final Logger LOG = LoggerFactory.getLogger(QCloudVODServiceImpl.class);

	private VodClient vodClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String uploadVideo(File mediaFile, File coverFile) throws CloudSDKException
	{
		MediaTypeEnum mediaType = getMediaType(mediaFile);
		CoverTypeEnum coverType = getCoverType(coverFile);

		COSClient cosClient = null;
		ObjectStorageService objectStorageService = null;

		String bucket = null;
		String coverObjectName = null;
		String mediaObjectName = null;

		try
		{
			ApplyUploadRequest req = new ApplyUploadRequest();
			req.setMediaType(mediaType.getValue());
			req.setCoverType(coverType.getValue());

			ApplyUploadResponse applyResponse = vodClient.ApplyUpload(req);

			COSProfile cosProfile = new COSProfile();
			cosProfile.setAccessKey(applyResponse.getTempCertificate().getSecretId());
			cosProfile.setSecretKey(applyResponse.getTempCertificate().getSecretKey());
			cosProfile.setSessionToken(applyResponse.getTempCertificate().getToken());
			cosProfile.setRegion(applyResponse.getStorageRegion());

			cosClient = COSClientBuilder.build(cosProfile);
			objectStorageService = new COSObjectStorageServiceImpl(cosClient, cosProfile);

			bucket = applyResponse.getStorageBucket();

			objectStorageService.putObject(bucket, applyResponse.getCoverStoragePath(), coverFile);
			coverObjectName = applyResponse.getCoverStoragePath();

			objectStorageService.putObject(bucket, applyResponse.getMediaStoragePath(), mediaFile);
			mediaObjectName = applyResponse.getMediaStoragePath();

			CommitUploadRequest commitRequest = new CommitUploadRequest();
			commitRequest.setVodSessionKey(applyResponse.getVodSessionKey());

			CommitUploadResponse commitResponse = vodClient.CommitUpload(commitRequest);

			// 上传成功后要置为NULL
			coverObjectName = null;
			mediaObjectName = null;

			return commitResponse.getFileId();
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
		finally
		{
			cleanUploadFailedFile(bucket, coverObjectName, objectStorageService);
			cleanUploadFailedFile(bucket, mediaObjectName, objectStorageService);

			if (null != cosClient)
			{
				cosClient.shutdown();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String snapshotCover(String fileId, long screenshotTmplId, float timePosition) throws CloudSDKException
	{
		try
		{
			ProcessMediaRequest req = new ProcessMediaRequest();
			MediaProcessTaskInput mediaProcessTaskInput = new MediaProcessTaskInput();

			CoverBySnapshotTaskInput[] coverBySnapshotTaskInputs = new CoverBySnapshotTaskInput[1];
			CoverBySnapshotTaskInput coverBySnapshotTaskInput = new CoverBySnapshotTaskInput();
			coverBySnapshotTaskInput.setDefinition(screenshotTmplId);
			coverBySnapshotTaskInput.setPositionType("Time");
			coverBySnapshotTaskInput.setPositionValue(timePosition);
			coverBySnapshotTaskInputs[0] = coverBySnapshotTaskInput;

			mediaProcessTaskInput.setCoverBySnapshotTaskSet(coverBySnapshotTaskInputs);

			req.setMediaProcessTask(mediaProcessTaskInput);

			req.setFileId(fileId);

			ProcessMediaResponse resp = vodClient.ProcessMedia(req);
			if (StringUtils.isBlank(resp.getTaskId()))
			{
				throw new CloudSDKException("Snapshot cover failed, response: " + ProcessMediaResponse.toJsonString(resp));
			}

			return resp.getTaskId();
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String transcode(String fileId, long transcodeTmplId, long screenshotTmplId, float timePosition) throws CloudSDKException
	{
		try
		{
			MediaProcessTaskInput mediaProcessTaskInput = new MediaProcessTaskInput();

			CoverBySnapshotTaskInput coverBySnapshotTaskInput = new CoverBySnapshotTaskInput();
			coverBySnapshotTaskInput.setDefinition(screenshotTmplId);
			coverBySnapshotTaskInput.setPositionType("Time");
			coverBySnapshotTaskInput.setPositionValue(timePosition);

			CoverBySnapshotTaskInput[] coverBySnapshotTaskInputs = new CoverBySnapshotTaskInput[1];
			coverBySnapshotTaskInputs[0] = coverBySnapshotTaskInput;
			mediaProcessTaskInput.setCoverBySnapshotTaskSet(coverBySnapshotTaskInputs);

			TranscodeTaskInput transcodeTaskInput = new TranscodeTaskInput();
			transcodeTaskInput.setDefinition(transcodeTmplId);
			TranscodeTaskInput[] transcodeTasks = new TranscodeTaskInput[1];
			transcodeTasks[0] = transcodeTaskInput;
			mediaProcessTaskInput.setTranscodeTaskSet(transcodeTasks);

			ProcessMediaRequest req = new ProcessMediaRequest();
			req.setMediaProcessTask(mediaProcessTaskInput);
			req.setFileId(fileId);

			ProcessMediaResponse resp = vodClient.ProcessMedia(req);
			if (StringUtils.isBlank(resp.getTaskId()))
			{
				throw new CloudSDKException("Transcode cover failed, response: " + ProcessMediaResponse.toJsonString(resp));
			}

			return resp.getTaskId();
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String modifyCover(String fileId, String base64Data) throws CloudSDKException
	{
		try
		{
			ModifyMediaInfoRequest req = new ModifyMediaInfoRequest();
			req.setCoverData(base64Data);
			req.setFileId(fileId);

			ModifyMediaInfoResponse resp = vodClient.ModifyMediaInfo(req);
			if (StringUtils.isBlank(resp.getCoverUrl()))
			{
				throw new CloudSDKException("Modify cover failed, response: " + ModifyMediaInfoResponse.toJsonString(resp));
			}

			return resp.getCoverUrl();
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteVideo(String fileId) throws CloudSDKException
	{
		try
		{
			DeleteMediaRequest req = new DeleteMediaRequest();
			req.setFileId(fileId);

			DeleteMediaResponse resp = vodClient.DeleteMedia(req);

			LOG.info("Delete video successfully. requestid is {}, fileId is {}.", resp.getRequestId(), fileId);
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MediaMeta getVideoMeta(String fileId) throws CloudSDKException
	{
		try
		{
			String[] fileIds = { fileId };

			DescribeMediaInfosRequest req = new DescribeMediaInfosRequest();
			req.setFileIds(fileIds);

			DescribeMediaInfosResponse resp = vodClient.DescribeMediaInfos(req);

			MediaInfo[] mediaInfos = resp.getMediaInfoSet();
			if (null != mediaInfos && 1 == mediaInfos.length)
			{
				MediaInfo mediaInfo = mediaInfos[0];

				MediaMeta meta = buildMediaMeta(fileId, mediaInfo.getBasicInfo(), mediaInfo.getMetaData(), mediaInfo.getTranscodeInfo());
				return meta;
			}
			else
			{
				return null;
			}
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VodEvent> pullEvents() throws CloudSDKException
	{
		try
		{
			PullEventsRequest req = new PullEventsRequest();
			PullEventsResponse resp = vodClient.PullEvents(req);

			EventContent[] list = resp.getEventSet();
			if (null != list)
			{
				List<VodEvent> events = new ArrayList<>(list.length);
				for (EventContent item : list)
				{
					VodEvent event = convertToEvent(item);
					events.add(event);
				}

				return events;
			}

			return new ArrayList<>(0);
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void confirmEvents(String[] eventIds) throws CloudSDKException
	{
		try
		{
			ConfirmEventsRequest req = new ConfirmEventsRequest();
			req.setEventHandles(eventIds);

			vodClient.ConfirmEvents(req);
		}
		catch (TencentCloudSDKException e)
		{
			throw new CloudSDKException(e.getErrorCode(), e.getMessage(), e);
		}
	}

	private VodEvent convertToEvent(EventContent content)
	{
		String eventId = content.getEventHandle();
		String eventType = content.getEventType();

		if ("NewFileUpload".equals(eventType))
		{
			FileUploadTask uploadTask = content.getFileUploadEvent();
			MediaMeta mediaMeta = buildMediaMeta(uploadTask.getFileId(), uploadTask.getMediaBasicInfo(), uploadTask.getMetaData(), null);

			return new VodNewFileUploadEvent(eventId, mediaMeta);
		}
		else if ("ProcedureStateChanged".equals(eventType))
		{
			ProcedureTask precTask = content.getProcedureStateChangeEvent();

			boolean completed = "FINISH".equals(precTask.getStatus());

			return new VodStateChangedEvent(eventId, precTask.getFileId(), completed);
		}
		else if ("FileDeleted".equals(eventType))
		{
			FileDeleteTask deleteTask = content.getFileDeleteEvent();
			return new VodFileDeletedEvent(eventId, deleteTask.getFileIdSet());
		}
		else
		{
			return new VodEvent(eventId);
		}
	}

	private MediaMeta buildMediaMeta(String fileId, MediaBasicInfo basicInfo, MediaMetaData metaData, MediaTranscodeInfo transcodeInfo)
	{
		MediaMeta midiaMeta = new MediaMeta();
		midiaMeta.setFileId(fileId);

		if (null != basicInfo)
		{
			midiaMeta.setName(basicInfo.getName());
			midiaMeta.setType(basicInfo.getType());
			midiaMeta.setMediaUrl(basicInfo.getMediaUrl());
			midiaMeta.setCoverUrl(basicInfo.getCoverUrl());
		}

		if (null != metaData)
		{
			midiaMeta.setDuration(metaData.getDuration().intValue());
			midiaMeta.setMediaFileSize(metaData.getSize());
			midiaMeta.setWidth(metaData.getWidth());
			midiaMeta.setHeight(metaData.getHeight());

			MediaVideoStreamItem[] videoStreams = metaData.getVideoStreamSet();
			if (!ArrayUtils.isEmpty(videoStreams))
			{
				MediaVideoStreamItem videoStream = videoStreams[0];
				String codec = videoStream.getCodec();
				midiaMeta.setCodec(codec);
			}
		}

		if (null != transcodeInfo)
		{
			MediaTranscodeItem[] list = ArrayUtils.nullToEmpty(transcodeInfo.getTranscodeSet(), MediaTranscodeItem[].class);
			for (int i = list.length - 1; i >= 0; --i)
			{
				MediaTranscodeItem item = list[i];

				MediaVideoStreamItem[] videoStreams = item.getVideoStreamSet();
				if (ArrayUtils.isEmpty(videoStreams))
				{
					continue;
				}

				MediaVideoStreamItem videoStream = videoStreams[0];
				String codec = videoStream.getCodec();
				if (MediaCodecEnum.H264.isSelf(codec))
				{
					midiaMeta.setCodec(codec);
					midiaMeta.setDuration(item.getDuration().intValue());
					midiaMeta.setMediaFileSize(item.getSize());
					midiaMeta.setMediaUrl(item.getUrl());
					midiaMeta.setWidth(item.getWidth());
					midiaMeta.setHeight(item.getHeight());

					break;
				}
			}
		}

		return midiaMeta;
	}

	private void cleanUploadFailedFile(String bucket, String objectName, ObjectStorageService objectStoreageService)
	{
		if (null != bucket && null != objectName && null != objectStoreageService)
		{
			try
			{
				objectStoreageService.deleteObject(bucket, objectName);
			}
			catch (Throwable e)
			{
				LOG.error("Delete object file failed, bucket is {}, object name is {}.", bucket, objectName, e);
			}
		}
	}

	private MediaTypeEnum getMediaType(File mediaFile) throws CloudSDKException
	{
		String extension = FilenameUtils.getExtension(mediaFile.getName());
		MediaTypeEnum mediaType = MediaTypeEnum.get(extension.toLowerCase(Locale.US));
		if (null == mediaType)
		{
			throw new CloudSDKException("Media type does not supported.");
		}

		return mediaType;
	}

	private CoverTypeEnum getCoverType(File coverFile) throws CloudSDKException
	{
		String extension = FilenameUtils.getExtension(coverFile.getName());
		CoverTypeEnum coverType = CoverTypeEnum.get(extension.toLowerCase(Locale.US));
		if (null == coverType)
		{
			throw new CloudSDKException("Cover type does not supported.");
		}

		return coverType;
	}

	public void init(VodProfile profile) throws CloudSDKException
	{
		VodProfileValidator.validate(profile);

		Credential cred = new Credential(profile.getAccessKey(), profile.getSecretKey());

		HttpProfile httpProfile = new HttpProfile();
		httpProfile.setEndpoint(profile.getEndpoint());

		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setHttpProfile(httpProfile);

		VodClient client = new VodClient(cred, profile.getRegion(), clientProfile);

		this.vodClient = client;

		LOG.info("Init vod client successfully.");
	}
}
