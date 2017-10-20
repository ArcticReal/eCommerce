package com.skytala.eCommerce.domain.content.relations.content.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assoc.ContentAssoc;
public class ContentAssocDeleted implements Event{

	private boolean success;

	public ContentAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
