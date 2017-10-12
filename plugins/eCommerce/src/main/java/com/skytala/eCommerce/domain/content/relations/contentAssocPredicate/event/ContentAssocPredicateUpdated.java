package com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.model.ContentAssocPredicate;
public class ContentAssocPredicateUpdated implements Event{

	private boolean success;

	public ContentAssocPredicateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
