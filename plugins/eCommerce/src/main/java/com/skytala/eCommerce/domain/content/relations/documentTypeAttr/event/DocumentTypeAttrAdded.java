package com.skytala.eCommerce.domain.content.relations.documentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.documentTypeAttr.model.DocumentTypeAttr;
public class DocumentTypeAttrAdded implements Event{

	private DocumentTypeAttr addedDocumentTypeAttr;
	private boolean success;

	public DocumentTypeAttrAdded(DocumentTypeAttr addedDocumentTypeAttr, boolean success){
		this.addedDocumentTypeAttr = addedDocumentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DocumentTypeAttr getAddedDocumentTypeAttr() {
		return addedDocumentTypeAttr;
	}

}
