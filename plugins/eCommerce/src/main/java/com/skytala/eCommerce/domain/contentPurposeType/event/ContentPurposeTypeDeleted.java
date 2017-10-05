package com.skytala.eCommerce.domain.contentPurposeType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentPurposeType.model.ContentPurposeType;
public class ContentPurposeTypeDeleted implements Event{

	private boolean success;

	public ContentPurposeTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
