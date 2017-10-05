package com.skytala.eCommerce.domain.contentAssocPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocPredicate.model.ContentAssocPredicate;
public class ContentAssocPredicateUpdated implements Event{

	private boolean success;

	public ContentAssocPredicateUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
