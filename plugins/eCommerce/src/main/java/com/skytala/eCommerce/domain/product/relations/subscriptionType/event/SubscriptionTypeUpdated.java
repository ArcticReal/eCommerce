package com.skytala.eCommerce.domain.product.relations.subscriptionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionType.model.SubscriptionType;
public class SubscriptionTypeUpdated implements Event{

	private boolean success;

	public SubscriptionTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
