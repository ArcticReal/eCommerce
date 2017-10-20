package com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.typeAttr.SubscriptionTypeAttr;
public class SubscriptionTypeAttrFound implements Event{

	private List<SubscriptionTypeAttr> subscriptionTypeAttrs;

	public SubscriptionTypeAttrFound(List<SubscriptionTypeAttr> subscriptionTypeAttrs) {
		this.subscriptionTypeAttrs = subscriptionTypeAttrs;
	}

	public List<SubscriptionTypeAttr> getSubscriptionTypeAttrs()	{
		return subscriptionTypeAttrs;
	}

}
