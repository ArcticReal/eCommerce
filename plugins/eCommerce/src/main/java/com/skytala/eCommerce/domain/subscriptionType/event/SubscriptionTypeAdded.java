package com.skytala.eCommerce.domain.subscriptionType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionType.model.SubscriptionType;
public class SubscriptionTypeAdded implements Event{

	private SubscriptionType addedSubscriptionType;
	private boolean success;

	public SubscriptionTypeAdded(SubscriptionType addedSubscriptionType, boolean success){
		this.addedSubscriptionType = addedSubscriptionType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionType getAddedSubscriptionType() {
		return addedSubscriptionType;
	}

}
