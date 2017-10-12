package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
public class ContentSearchConstraintDeleted implements Event{

	private boolean success;

	public ContentSearchConstraintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
