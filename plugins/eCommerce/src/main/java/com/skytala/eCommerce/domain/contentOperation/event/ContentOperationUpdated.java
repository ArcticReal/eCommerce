package com.skytala.eCommerce.domain.contentOperation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentOperation.model.ContentOperation;
public class ContentOperationUpdated implements Event{

	private boolean success;

	public ContentOperationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
