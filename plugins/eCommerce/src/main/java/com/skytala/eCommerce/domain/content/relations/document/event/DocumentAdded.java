package com.skytala.eCommerce.domain.content.relations.document.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.Document;
public class DocumentAdded implements Event{

	private Document addedDocument;
	private boolean success;

	public DocumentAdded(Document addedDocument, boolean success){
		this.addedDocument = addedDocument;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Document getAddedDocument() {
		return addedDocument;
	}

}
