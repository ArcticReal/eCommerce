package com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purposeOperation.ContentPurposeOperation;
public class ContentPurposeOperationFound implements Event{

	private List<ContentPurposeOperation> contentPurposeOperations;

	public ContentPurposeOperationFound(List<ContentPurposeOperation> contentPurposeOperations) {
		this.contentPurposeOperations = contentPurposeOperations;
	}

	public List<ContentPurposeOperation> getContentPurposeOperations()	{
		return contentPurposeOperations;
	}

}
