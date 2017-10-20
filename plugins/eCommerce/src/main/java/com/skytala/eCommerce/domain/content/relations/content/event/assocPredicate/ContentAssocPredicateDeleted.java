package com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;
public class ContentAssocPredicateDeleted implements Event{

	private boolean success;

	public ContentAssocPredicateDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
