package com.skytala.eCommerce.domain.content.relations.dataResource.event.image;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.dataResource.model.image.ImageDataResource;
public class ImageDataResourceFound implements Event{

	private List<ImageDataResource> imageDataResources;

	public ImageDataResourceFound(List<ImageDataResource> imageDataResources) {
		this.imageDataResources = imageDataResources;
	}

	public List<ImageDataResource> getImageDataResources()	{
		return imageDataResources;
	}

}
