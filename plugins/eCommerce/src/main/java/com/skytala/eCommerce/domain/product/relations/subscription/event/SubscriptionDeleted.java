package com.skytala.eCommerce.domain.product.relations.subscription.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;
public class SubscriptionDeleted implements Event{

	private boolean success;

	public SubscriptionDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
