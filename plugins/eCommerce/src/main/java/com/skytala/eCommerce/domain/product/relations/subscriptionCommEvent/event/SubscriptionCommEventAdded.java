package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;
public class SubscriptionCommEventAdded implements Event{

	private SubscriptionCommEvent addedSubscriptionCommEvent;
	private boolean success;

	public SubscriptionCommEventAdded(SubscriptionCommEvent addedSubscriptionCommEvent, boolean success){
		this.addedSubscriptionCommEvent = addedSubscriptionCommEvent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionCommEvent getAddedSubscriptionCommEvent() {
		return addedSubscriptionCommEvent;
	}

}
