package com.skytala.eCommerce.domain.party.relations.addendum.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.addendum.model.Addendum;
public class AddendumDeleted implements Event{

	private boolean success;

	public AddendumDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
