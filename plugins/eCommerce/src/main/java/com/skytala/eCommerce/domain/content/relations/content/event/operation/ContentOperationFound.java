package com.skytala.eCommerce.domain.content.relations.content.event.operation;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
public class ContentOperationFound implements Event{

	private List<ContentOperation> contentOperations;

	public ContentOperationFound(List<ContentOperation> contentOperations) {
		this.contentOperations = contentOperations;
	}

	public List<ContentOperation> getContentOperations()	{
		return contentOperations;
	}

}
