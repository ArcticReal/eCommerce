package com.skytala.eCommerce.domain.product.relations.subscription.event.resource;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.resource.SubscriptionResource;
public class SubscriptionResourceFound implements Event{

	private List<SubscriptionResource> subscriptionResources;

	public SubscriptionResourceFound(List<SubscriptionResource> subscriptionResources) {
		this.subscriptionResources = subscriptionResources;
	}

	public List<SubscriptionResource> getSubscriptionResources()	{
		return subscriptionResources;
	}

}
