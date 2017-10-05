package com.skytala.eCommerce.domain.addendum.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.addendum.model.Addendum;
public class AddendumUpdated implements Event{

	private boolean success;

	public AddendumUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
