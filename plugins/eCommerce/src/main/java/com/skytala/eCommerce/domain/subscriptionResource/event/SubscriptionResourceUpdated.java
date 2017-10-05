package com.skytala.eCommerce.domain.subscriptionResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionResource.model.SubscriptionResource;
public class SubscriptionResourceUpdated implements Event{

	private boolean success;

	public SubscriptionResourceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
