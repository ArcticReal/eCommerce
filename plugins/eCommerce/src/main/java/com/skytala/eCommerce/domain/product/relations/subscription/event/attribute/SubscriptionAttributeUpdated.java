package com.skytala.eCommerce.domain.product.relations.subscription.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.attribute.SubscriptionAttribute;
public class SubscriptionAttributeUpdated implements Event{

	private boolean success;

	public SubscriptionAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
