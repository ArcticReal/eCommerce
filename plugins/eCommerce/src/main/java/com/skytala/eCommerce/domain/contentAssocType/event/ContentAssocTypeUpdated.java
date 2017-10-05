package com.skytala.eCommerce.domain.contentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocType.model.ContentAssocType;
public class ContentAssocTypeUpdated implements Event{

	private boolean success;

	public ContentAssocTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
