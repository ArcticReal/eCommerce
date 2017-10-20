package com.skytala.eCommerce.domain.product.relations.subscription.event.activity;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;
public class SubscriptionActivityUpdated implements Event{

	private boolean success;

	public SubscriptionActivityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
