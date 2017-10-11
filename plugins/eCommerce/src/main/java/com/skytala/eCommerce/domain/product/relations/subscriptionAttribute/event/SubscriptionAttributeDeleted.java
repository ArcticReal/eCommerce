package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;
public class SubscriptionAttributeDeleted implements Event{

	private boolean success;

	public SubscriptionAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
