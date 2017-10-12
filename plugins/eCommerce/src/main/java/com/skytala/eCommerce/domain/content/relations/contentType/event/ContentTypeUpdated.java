package com.skytala.eCommerce.domain.content.relations.contentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentType.model.ContentType;
public class ContentTypeUpdated implements Event{

	private boolean success;

	public ContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
