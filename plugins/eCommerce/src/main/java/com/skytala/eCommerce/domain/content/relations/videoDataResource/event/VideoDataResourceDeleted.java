package com.skytala.eCommerce.domain.content.relations.videoDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;
public class VideoDataResourceDeleted implements Event{

	private boolean success;

	public VideoDataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
