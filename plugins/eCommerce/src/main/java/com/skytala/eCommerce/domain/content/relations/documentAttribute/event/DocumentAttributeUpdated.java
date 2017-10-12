package com.skytala.eCommerce.domain.content.relations.documentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.documentAttribute.model.DocumentAttribute;
public class DocumentAttributeUpdated implements Event{

	private boolean success;

	public DocumentAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
