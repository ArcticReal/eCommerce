package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;
public class SubscriptionActivityUpdated implements Event{

	private boolean success;

	public SubscriptionActivityUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
