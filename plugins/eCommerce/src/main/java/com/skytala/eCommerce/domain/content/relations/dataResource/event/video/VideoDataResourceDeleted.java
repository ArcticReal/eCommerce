package com.skytala.eCommerce.domain.content.relations.dataResource.event.video;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.video.VideoDataResource;
public class VideoDataResourceDeleted implements Event{

	private boolean success;

	public VideoDataResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
