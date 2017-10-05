package com.skytala.eCommerce.domain.contentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentType.model.ContentType;
public class ContentTypeUpdated implements Event{

	private boolean success;

	public ContentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
