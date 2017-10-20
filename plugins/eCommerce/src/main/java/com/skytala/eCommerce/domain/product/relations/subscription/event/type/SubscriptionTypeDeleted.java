package com.skytala.eCommerce.domain.product.relations.subscription.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;
public class SubscriptionTypeDeleted implements Event{

	private boolean success;

	public SubscriptionTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
