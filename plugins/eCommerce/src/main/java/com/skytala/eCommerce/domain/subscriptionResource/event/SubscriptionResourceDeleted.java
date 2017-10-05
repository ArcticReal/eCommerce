package com.skytala.eCommerce.domain.subscriptionResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionResource.model.SubscriptionResource;
public class SubscriptionResourceDeleted implements Event{

	private boolean success;

	public SubscriptionResourceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
