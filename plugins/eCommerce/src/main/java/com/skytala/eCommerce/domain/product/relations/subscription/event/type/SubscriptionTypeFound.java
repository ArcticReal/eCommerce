package com.skytala.eCommerce.domain.product.relations.subscription.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;
public class SubscriptionTypeFound implements Event{

	private List<SubscriptionType> subscriptionTypes;

	public SubscriptionTypeFound(List<SubscriptionType> subscriptionTypes) {
		this.subscriptionTypes = subscriptionTypes;
	}

	public List<SubscriptionType> getSubscriptionTypes()	{
		return subscriptionTypes;
	}

}
