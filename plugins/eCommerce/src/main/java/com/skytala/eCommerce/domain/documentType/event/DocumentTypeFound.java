package com.skytala.eCommerce.domain.documentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.documentType.model.DocumentType;
public class DocumentTypeFound implements Event{

	private List<DocumentType> documentTypes;

	public DocumentTypeFound(List<DocumentType> documentTypes) {
		this.documentTypes = documentTypes;
	}

	public List<DocumentType> getDocumentTypes()	{
		return documentTypes;
	}

}
