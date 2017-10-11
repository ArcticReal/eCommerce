package com.skytala.eCommerce.domain.product.relations.subscription.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.Subscription;
public class SubscriptionAdded implements Event{

	private Subscription addedSubscription;
	private boolean success;

	public SubscriptionAdded(Subscription addedSubscription, boolean success){
		this.addedSubscription = addedSubscription;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Subscription getAddedSubscription() {
		return addedSubscription;
	}

}
