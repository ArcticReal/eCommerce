package com.skytala.eCommerce.domain.content.relations.contentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentType.model.ContentType;
public class ContentTypeDeleted implements Event{

	private boolean success;

	public ContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
