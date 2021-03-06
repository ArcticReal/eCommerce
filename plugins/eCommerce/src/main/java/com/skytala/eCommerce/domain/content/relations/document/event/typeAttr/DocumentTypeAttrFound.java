package com.skytala.eCommerce.domain.content.relations.document.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.typeAttr.DocumentTypeAttr;
public class DocumentTypeAttrFound implements Event{

	private List<DocumentTypeAttr> documentTypeAttrs;

	public DocumentTypeAttrFound(List<DocumentTypeAttr> documentTypeAttrs) {
		this.documentTypeAttrs = documentTypeAttrs;
	}

	public List<DocumentTypeAttr> getDocumentTypeAttrs()	{
		return documentTypeAttrs;
	}

}
