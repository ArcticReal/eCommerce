package com.skytala.eCommerce.domain.content.relations.document.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.Document;
public class DocumentUpdated implements Event{

	private boolean success;

	public DocumentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
