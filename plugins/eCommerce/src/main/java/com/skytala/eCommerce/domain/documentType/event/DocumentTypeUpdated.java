package com.skytala.eCommerce.domain.documentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.documentType.model.DocumentType;
public class DocumentTypeUpdated implements Event{

	private boolean success;

	public DocumentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
