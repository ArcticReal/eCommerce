package com.skytala.eCommerce.domain.contentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentType.model.ContentType;
public class ContentTypeDeleted implements Event{

	private boolean success;

	public ContentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
