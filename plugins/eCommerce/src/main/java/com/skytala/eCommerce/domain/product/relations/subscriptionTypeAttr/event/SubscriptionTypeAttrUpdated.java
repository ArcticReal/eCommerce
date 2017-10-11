package com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.model.SubscriptionTypeAttr;
public class SubscriptionTypeAttrUpdated implements Event{

	private boolean success;

	public SubscriptionTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
