package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;
public class SubscriptionActivityFound implements Event{

	private List<SubscriptionActivity> subscriptionActivitys;

	public SubscriptionActivityFound(List<SubscriptionActivity> subscriptionActivitys) {
		this.subscriptionActivitys = subscriptionActivitys;
	}

	public List<SubscriptionActivity> getSubscriptionActivitys()	{
		return subscriptionActivitys;
	}

}
