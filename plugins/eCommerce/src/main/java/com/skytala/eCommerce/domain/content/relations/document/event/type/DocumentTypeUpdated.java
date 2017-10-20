package com.skytala.eCommerce.domain.content.relations.document.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.type.DocumentType;
public class DocumentTypeUpdated implements Event{

	private boolean success;

	public DocumentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
