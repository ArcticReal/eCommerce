package com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent.SubscriptionCommEvent;
public class SubscriptionCommEventUpdated implements Event{

	private boolean success;

	public SubscriptionCommEventUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
