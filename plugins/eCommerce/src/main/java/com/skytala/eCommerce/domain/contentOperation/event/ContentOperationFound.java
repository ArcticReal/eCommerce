package com.skytala.eCommerce.domain.contentOperation.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentOperation.model.ContentOperation;
public class ContentOperationFound implements Event{

	private List<ContentOperation> contentOperations;

	public ContentOperationFound(List<ContentOperation> contentOperations) {
		this.contentOperations = contentOperations;
	}

	public List<ContentOperation> getContentOperations()	{
		return contentOperations;
	}

}
