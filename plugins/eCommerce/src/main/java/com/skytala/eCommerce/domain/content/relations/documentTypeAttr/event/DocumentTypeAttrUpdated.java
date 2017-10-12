package com.skytala.eCommerce.domain.content.relations.documentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.model.DocumentTypeAttr;
public class DocumentTypeAttrUpdated implements Event{

	private boolean success;

	public DocumentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
