package com.skytala.eCommerce.domain.content.relations.content.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
public class ContentTypeDeleted implements Event{

	private boolean success;

	public ContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
