package com.skytala.eCommerce.domain.content.relations.document.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.typeAttr.DocumentTypeAttr;
public class DocumentTypeAttrDeleted implements Event{

	private boolean success;

	public DocumentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
