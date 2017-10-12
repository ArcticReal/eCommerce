package com.skytala.eCommerce.domain.content.relations.videoDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;
public class VideoDataResourceAdded implements Event{

	private VideoDataResource addedVideoDataResource;
	private boolean success;

	public VideoDataResourceAdded(VideoDataResource addedVideoDataResource, boolean success){
		this.addedVideoDataResource = addedVideoDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public VideoDataResource getAddedVideoDataResource() {
		return addedVideoDataResource;
	}

}
