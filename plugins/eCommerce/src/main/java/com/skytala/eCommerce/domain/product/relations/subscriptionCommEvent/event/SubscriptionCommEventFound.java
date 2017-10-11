package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;
public class SubscriptionCommEventFound implements Event{

	private List<SubscriptionCommEvent> subscriptionCommEvents;

	public SubscriptionCommEventFound(List<SubscriptionCommEvent> subscriptionCommEvents) {
		this.subscriptionCommEvents = subscriptionCommEvents;
	}

	public List<SubscriptionCommEvent> getSubscriptionCommEvents()	{
		return subscriptionCommEvents;
	}

}
