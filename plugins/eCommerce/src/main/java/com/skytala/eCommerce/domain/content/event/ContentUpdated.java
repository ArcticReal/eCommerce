package com.skytala.eCommerce.domain.content.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.model.Content;
public class ContentUpdated implements Event{

	private boolean success;

	public ContentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
