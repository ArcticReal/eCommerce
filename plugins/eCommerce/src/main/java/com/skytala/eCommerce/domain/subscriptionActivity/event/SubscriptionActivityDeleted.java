package com.skytala.eCommerce.domain.subscriptionActivity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionActivity.model.SubscriptionActivity;
public class SubscriptionActivityDeleted implements Event{

	private boolean success;

	public SubscriptionActivityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
