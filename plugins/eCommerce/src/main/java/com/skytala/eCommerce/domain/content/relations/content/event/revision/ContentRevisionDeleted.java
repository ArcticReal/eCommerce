package com.skytala.eCommerce.domain.content.relations.content.event.revision;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;
public class ContentRevisionDeleted implements Event{

	private boolean success;

	public ContentRevisionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
