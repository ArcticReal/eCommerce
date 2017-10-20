package com.skytala.eCommerce.domain.product.relations.subscription.event.activity;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;
public class SubscriptionActivityFound implements Event{

	private List<SubscriptionActivity> subscriptionActivitys;

	public SubscriptionActivityFound(List<SubscriptionActivity> subscriptionActivitys) {
		this.subscriptionActivitys = subscriptionActivitys;
	}

	public List<SubscriptionActivity> getSubscriptionActivitys()	{
		return subscriptionActivitys;
	}

}
