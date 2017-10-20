package com.skytala.eCommerce.domain.content.relations.document.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.type.DocumentType;
public class DocumentTypeFound implements Event{

	private List<DocumentType> documentTypes;

	public DocumentTypeFound(List<DocumentType> documentTypes) {
		this.documentTypes = documentTypes;
	}

	public List<DocumentType> getDocumentTypes()	{
		return documentTypes;
	}

}
