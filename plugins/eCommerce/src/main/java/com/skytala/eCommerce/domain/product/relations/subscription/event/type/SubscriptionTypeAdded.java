package com.skytala.eCommerce.domain.product.relations.subscription.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.type.SubscriptionType;
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
