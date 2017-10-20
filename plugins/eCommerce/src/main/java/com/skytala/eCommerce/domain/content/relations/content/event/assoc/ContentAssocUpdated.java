package com.skytala.eCommerce.domain.content.relations.content.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assoc.ContentAssoc;
public class ContentAssocUpdated implements Event{

	private boolean success;

	public ContentAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
