package com.skytala.eCommerce.domain.content.relations.imageDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.imageDataResource.model.ImageDataResource;
public class ImageDataResourceUpdated implements Event{

	private boolean success;

	public ImageDataResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
