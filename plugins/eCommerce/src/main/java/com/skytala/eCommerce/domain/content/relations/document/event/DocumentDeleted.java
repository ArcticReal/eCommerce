package com.skytala.eCommerce.domain.content.relations.document.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.Document;
public class DocumentDeleted implements Event{

	private boolean success;

	public DocumentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
