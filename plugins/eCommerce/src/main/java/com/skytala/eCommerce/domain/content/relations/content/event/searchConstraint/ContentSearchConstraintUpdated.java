package com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.searchConstraint.ContentSearchConstraint;
public class ContentSearchConstraintUpdated implements Event{

	private boolean success;

	public ContentSearchConstraintUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
