package com.skytala.eCommerce.domain.content.relations.content.event.revisionItem;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.revisionItem.ContentRevisionItem;
public class ContentRevisionItemUpdated implements Event{

	private boolean success;

	public ContentRevisionItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
