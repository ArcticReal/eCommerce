package com.skytala.eCommerce.domain.documentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.documentType.model.DocumentType;
public class DocumentTypeDeleted implements Event{

	private boolean success;

	public DocumentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
