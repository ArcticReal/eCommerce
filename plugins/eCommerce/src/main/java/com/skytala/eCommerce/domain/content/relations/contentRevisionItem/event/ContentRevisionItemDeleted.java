package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;
public class ContentRevisionItemDeleted implements Event{

	private boolean success;

	public ContentRevisionItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
