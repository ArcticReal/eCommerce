package com.skytala.eCommerce.domain.contentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocType.model.ContentAssocType;
public class ContentAssocTypeDeleted implements Event{

	private boolean success;

	public ContentAssocTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
