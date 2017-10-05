package com.skytala.eCommerce.domain.contentOperation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentOperation.model.ContentOperation;
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
