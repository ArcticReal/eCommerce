package com.skytala.eCommerce.domain.contentAssocPredicate.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocPredicate.model.ContentAssocPredicate;
public class ContentAssocPredicateDeleted implements Event{

	private boolean success;

	public ContentAssocPredicateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}