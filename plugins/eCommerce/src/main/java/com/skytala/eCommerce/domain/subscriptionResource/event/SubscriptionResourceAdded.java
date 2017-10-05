package com.skytala.eCommerce.domain.subscriptionResource.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.subscriptionResource.model.SubscriptionResource;
public class SubscriptionResourceAdded implements Event{

	private SubscriptionResource addedSubscriptionResource;
	private boolean success;

	public SubscriptionResourceAdded(SubscriptionResource addedSubscriptionResource, boolean success){
		this.addedSubscriptionResource = addedSubscriptionResource;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionResource getAddedSubscriptionResource() {
		return addedSubscriptionResource;
	}

}
