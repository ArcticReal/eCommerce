package com.skytala.eCommerce.domain.content.relations.document.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.attribute.DocumentAttribute;
public class DocumentAttributeFound implements Event{

	private List<DocumentAttribute> documentAttributes;

	public DocumentAttributeFound(List<DocumentAttribute> documentAttributes) {
		this.documentAttributes = documentAttributes;
	}

	public List<DocumentAttribute> getDocumentAttributes()	{
		return documentAttributes;
	}

}
