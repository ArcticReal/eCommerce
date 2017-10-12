package com.skytala.eCommerce.domain.content.relations.contentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;
public class ContentAttributeUpdated implements Event{

	private boolean success;

	public ContentAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
