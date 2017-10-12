package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
public class ContentSearchConstraintUpdated implements Event{

	private boolean success;

	public ContentSearchConstraintUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
