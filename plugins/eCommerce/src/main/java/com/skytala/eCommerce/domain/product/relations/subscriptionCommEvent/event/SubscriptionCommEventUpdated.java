package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;
public class SubscriptionCommEventUpdated implements Event{

	private boolean success;

	public SubscriptionCommEventUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
