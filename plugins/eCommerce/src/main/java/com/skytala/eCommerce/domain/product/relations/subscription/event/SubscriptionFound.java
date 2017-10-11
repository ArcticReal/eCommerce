package com.skytala.eCommerce.domain.product.relations.subscription.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;
public class SubscriptionFound implements Event{

	private List<Subscription> subscriptions;

	public SubscriptionFound(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Subscription> getSubscriptions()	{
		return subscriptions;
	}

}
