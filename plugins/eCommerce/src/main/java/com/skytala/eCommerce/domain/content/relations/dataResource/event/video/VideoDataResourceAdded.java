package com.skytala.eCommerce.domain.content.relations.dataResource.event.video;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.video.VideoDataResource;
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
