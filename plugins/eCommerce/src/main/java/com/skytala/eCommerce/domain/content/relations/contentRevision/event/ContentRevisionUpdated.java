package com.skytala.eCommerce.domain.content.relations.contentRevision.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevision.model.ContentRevision;
public class ContentRevisionUpdated implements Event{

	private boolean success;

	public ContentRevisionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
