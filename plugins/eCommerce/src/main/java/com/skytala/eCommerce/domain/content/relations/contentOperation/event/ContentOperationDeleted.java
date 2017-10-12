package com.skytala.eCommerce.domain.content.relations.contentOperation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentOperation.model.ContentOperation;
public class ContentOperationDeleted implements Event{

	private boolean success;

	public ContentOperationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
