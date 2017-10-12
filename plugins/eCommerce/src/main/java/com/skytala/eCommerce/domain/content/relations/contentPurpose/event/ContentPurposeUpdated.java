package com.skytala.eCommerce.domain.content.relations.contentPurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentPurpose.model.ContentPurpose;
public class ContentPurposeUpdated implements Event{

	private boolean success;

	public ContentPurposeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
