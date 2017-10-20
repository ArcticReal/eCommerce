package com.skytala.eCommerce.domain.content.relations.document.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.type.DocumentType;
public class DocumentTypeAdded implements Event{

	private DocumentType addedDocumentType;
	private boolean success;

	public DocumentTypeAdded(DocumentType addedDocumentType, boolean success){
		this.addedDocumentType = addedDocumentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DocumentType getAddedDocumentType() {
		return addedDocumentType;
	}

}
