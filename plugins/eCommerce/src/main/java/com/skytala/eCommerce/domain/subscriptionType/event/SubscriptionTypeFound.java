package com.skytala.eCommerce.domain.subscriptionType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionType.model.SubscriptionType;
public class SubscriptionTypeFound implements Event{

	private List<SubscriptionType> subscriptionTypes;

	public SubscriptionTypeFound(List<SubscriptionType> subscriptionTypes) {
		this.subscriptionTypes = subscriptionTypes;
	}

	public List<SubscriptionType> getSubscriptionTypes()	{
		return subscriptionTypes;
	}

}
