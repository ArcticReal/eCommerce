package com.skytala.eCommerce.domain.content.relations.contentAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;
public class ContentAssocUpdated implements Event{

	private boolean success;

	public ContentAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
