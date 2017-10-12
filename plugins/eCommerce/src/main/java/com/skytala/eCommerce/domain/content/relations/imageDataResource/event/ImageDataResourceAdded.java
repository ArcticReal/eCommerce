package com.skytala.eCommerce.domain.content.relations.imageDataResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.imageDataResource.model.ImageDataResource;
public class ImageDataResourceAdded implements Event{

	private ImageDataResource addedImageDataResource;
	private boolean success;

	public ImageDataResourceAdded(ImageDataResource addedImageDataResource, boolean success){
		this.addedImageDataResource = addedImageDataResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ImageDataResource getAddedImageDataResource() {
		return addedImageDataResource;
	}

}
