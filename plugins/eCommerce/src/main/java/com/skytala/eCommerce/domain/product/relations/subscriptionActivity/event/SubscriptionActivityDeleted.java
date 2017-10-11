package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;
public class SubscriptionActivityDeleted implements Event{

	private boolean success;

	public SubscriptionActivityDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
