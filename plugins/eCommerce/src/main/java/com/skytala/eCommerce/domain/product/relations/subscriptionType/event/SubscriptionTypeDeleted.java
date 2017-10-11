package com.skytala.eCommerce.domain.product.relations.subscriptionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionType.model.SubscriptionType;
public class SubscriptionTypeDeleted implements Event{

	private boolean success;

	public SubscriptionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
