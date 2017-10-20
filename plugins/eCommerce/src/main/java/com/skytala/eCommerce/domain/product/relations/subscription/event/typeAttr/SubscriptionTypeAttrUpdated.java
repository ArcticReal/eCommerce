package com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.typeAttr.SubscriptionTypeAttr;
public class SubscriptionTypeAttrUpdated implements Event{

	private boolean success;

	public SubscriptionTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
