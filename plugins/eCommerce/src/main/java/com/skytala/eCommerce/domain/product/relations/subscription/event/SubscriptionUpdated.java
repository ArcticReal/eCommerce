package com.skytala.eCommerce.domain.product.relations.subscription.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;
public class SubscriptionUpdated implements Event{

	private boolean success;

	public SubscriptionUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
