package com.skytala.eCommerce.domain.content.relations.content.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purposeType.ContentPurposeType;
public class ContentPurposeTypeDeleted implements Event{

	private boolean success;

	public ContentPurposeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
