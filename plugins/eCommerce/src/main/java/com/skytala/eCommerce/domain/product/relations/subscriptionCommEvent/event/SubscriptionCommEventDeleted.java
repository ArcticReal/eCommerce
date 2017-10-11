package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;
public class SubscriptionCommEventDeleted implements Event{

	private boolean success;

	public SubscriptionCommEventDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
