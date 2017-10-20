package com.skytala.eCommerce.domain.content.relations.content.event.operation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.operation.ContentOperation;
public class ContentOperationDeleted implements Event{

	private boolean success;

	public ContentOperationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
