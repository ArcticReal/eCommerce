package com.skytala.eCommerce.domain.content.relations.content.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
public class ContentPurposeDeleted implements Event{

	private boolean success;

	public ContentPurposeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
