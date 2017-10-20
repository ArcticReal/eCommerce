package com.skytala.eCommerce.domain.product.relations.subscription.event.activity;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.activity.SubscriptionActivity;
public class SubscriptionActivityAdded implements Event{

	private SubscriptionActivity addedSubscriptionActivity;
	private boolean success;

	public SubscriptionActivityAdded(SubscriptionActivity addedSubscriptionActivity, boolean success){
		this.addedSubscriptionActivity = addedSubscriptionActivity;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionActivity getAddedSubscriptionActivity() {
		return addedSubscriptionActivity;
	}

}
