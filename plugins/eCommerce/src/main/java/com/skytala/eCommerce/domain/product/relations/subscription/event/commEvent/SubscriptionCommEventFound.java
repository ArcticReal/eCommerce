package com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent.SubscriptionCommEvent;
public class SubscriptionCommEventFound implements Event{

	private List<SubscriptionCommEvent> subscriptionCommEvents;

	public SubscriptionCommEventFound(List<SubscriptionCommEvent> subscriptionCommEvents) {
		this.subscriptionCommEvents = subscriptionCommEvents;
	}

	public List<SubscriptionCommEvent> getSubscriptionCommEvents()	{
		return subscriptionCommEvents;
	}

}
