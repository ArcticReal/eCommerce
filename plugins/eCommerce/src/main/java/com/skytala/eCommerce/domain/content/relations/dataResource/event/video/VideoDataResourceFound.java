package com.skytala.eCommerce.domain.content.relations.dataResource.event.video;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.video.VideoDataResource;
public class VideoDataResourceFound implements Event{

	private List<VideoDataResource> videoDataResources;

	public VideoDataResourceFound(List<VideoDataResource> videoDataResources) {
		this.videoDataResources = videoDataResources;
	}

	public List<VideoDataResource> getVideoDataResources()	{
		return videoDataResources;
	}

}
