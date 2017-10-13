package com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentPurposeOperation.model.ContentPurposeOperation;
public class ContentPurposeOperationDeleted implements Event{

	private boolean success;

	public ContentPurposeOperationDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}