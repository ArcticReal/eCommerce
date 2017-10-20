package com.skytala.eCommerce.domain.product.relations.subscription.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.attribute.SubscriptionAttribute;
public class SubscriptionAttributeAdded implements Event{

	private SubscriptionAttribute addedSubscriptionAttribute;
	private boolean success;

	public SubscriptionAttributeAdded(SubscriptionAttribute addedSubscriptionAttribute, boolean success){
		this.addedSubscriptionAttribute = addedSubscriptionAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionAttribute getAddedSubscriptionAttribute() {
		return addedSubscriptionAttribute;
	}

}
