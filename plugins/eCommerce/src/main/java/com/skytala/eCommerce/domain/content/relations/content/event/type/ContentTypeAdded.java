package com.skytala.eCommerce.domain.content.relations.content.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
public class ContentTypeAdded implements Event{

	private ContentType addedContentType;
	private boolean success;

	public ContentTypeAdded(ContentType addedContentType, boolean success){
		this.addedContentType = addedContentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentType getAddedContentType() {
		return addedContentType;
	}

}
