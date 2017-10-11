package com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.model.SubscriptionTypeAttr;
public class SubscriptionTypeAttrAdded implements Event{

	private SubscriptionTypeAttr addedSubscriptionTypeAttr;
	private boolean success;

	public SubscriptionTypeAttrAdded(SubscriptionTypeAttr addedSubscriptionTypeAttr, boolean success){
		this.addedSubscriptionTypeAttr = addedSubscriptionTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SubscriptionTypeAttr getAddedSubscriptionTypeAttr() {
		return addedSubscriptionTypeAttr;
	}

}
