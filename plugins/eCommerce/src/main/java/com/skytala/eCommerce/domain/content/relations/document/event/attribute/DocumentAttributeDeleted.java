package com.skytala.eCommerce.domain.content.relations.document.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.attribute.DocumentAttribute;
public class DocumentAttributeDeleted implements Event{

	private boolean success;

	public DocumentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
