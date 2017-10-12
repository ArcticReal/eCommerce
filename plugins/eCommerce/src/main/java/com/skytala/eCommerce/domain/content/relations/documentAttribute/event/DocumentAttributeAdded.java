package com.skytala.eCommerce.domain.content.relations.documentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.documentAttribute.model.DocumentAttribute;
public class DocumentAttributeAdded implements Event{

	private DocumentAttribute addedDocumentAttribute;
	private boolean success;

	public DocumentAttributeAdded(DocumentAttribute addedDocumentAttribute, boolean success){
		this.addedDocumentAttribute = addedDocumentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public DocumentAttribute getAddedDocumentAttribute() {
		return addedDocumentAttribute;
	}

}
