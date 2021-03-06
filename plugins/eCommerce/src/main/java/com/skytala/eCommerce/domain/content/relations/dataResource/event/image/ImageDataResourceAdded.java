package com.skytala.eCommerce.domain.content.relations.dataResource.event.image;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.image.ImageDataResource;
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
