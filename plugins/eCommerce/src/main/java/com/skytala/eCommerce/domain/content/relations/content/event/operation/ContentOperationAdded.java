package com.skytala.eCommerce.domain.content.relations.content.event.operation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
public class ContentOperationAdded implements Event{

	private ContentOperation addedContentOperation;
	private boolean success;

	public ContentOperationAdded(ContentOperation addedContentOperation, boolean success){
		this.addedContentOperation = addedContentOperation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentOperation getAddedContentOperation() {
		return addedContentOperation;
	}

}
