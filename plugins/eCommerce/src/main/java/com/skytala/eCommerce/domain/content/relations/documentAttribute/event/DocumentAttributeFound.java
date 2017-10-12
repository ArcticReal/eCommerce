package com.skytala.eCommerce.domain.content.relations.documentAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.documentAttribute.model.DocumentAttribute;
public class DocumentAttributeFound implements Event{

	private List<DocumentAttribute> documentAttributes;

	public DocumentAttributeFound(List<DocumentAttribute> documentAttributes) {
		this.documentAttributes = documentAttributes;
	}

	public List<DocumentAttribute> getDocumentAttributes()	{
		return documentAttributes;
	}

}
