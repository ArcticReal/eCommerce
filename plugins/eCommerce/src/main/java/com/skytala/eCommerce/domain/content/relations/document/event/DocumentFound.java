package com.skytala.eCommerce.domain.content.relations.document.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.document.model.Document;
public class DocumentFound implements Event{

	private List<Document> documents;

	public DocumentFound(List<Document> documents) {
		this.documents = documents;
	}

	public List<Document> getDocuments()	{
		return documents;
	}

}
