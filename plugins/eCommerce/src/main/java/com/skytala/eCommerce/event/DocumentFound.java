package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.entity.Party;
import com.skytala.eCommerce.control.Event;

public class DocumentFound implements Event{

	private List<Document> documents;

	public DocumentFound(List<Document> documents) {
		this.setDocuments(documents);
	}

	public List<Document> getDocuments()	{
		return documents;
	}

	public void setDocuments(List<Document> documents)	{
		this.documents = documents;
	}
}
