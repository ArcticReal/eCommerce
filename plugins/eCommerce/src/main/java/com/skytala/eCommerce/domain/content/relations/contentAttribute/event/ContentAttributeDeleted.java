package com.skytala.eCommerce.domain.content.relations.contentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;
public class ContentAttributeDeleted implements Event{

	private boolean success;

	public ContentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
