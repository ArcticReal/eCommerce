package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model.ContentPurposeOperation;
public class ContentPurposeOperationUpdated implements Event{

	private boolean success;

	public ContentPurposeOperationUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
