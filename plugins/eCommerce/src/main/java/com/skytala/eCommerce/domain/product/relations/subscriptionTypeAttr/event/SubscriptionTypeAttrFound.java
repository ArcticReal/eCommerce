package com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionTypeAttr.model.SubscriptionTypeAttr;
public class SubscriptionTypeAttrFound implements Event{

	private List<SubscriptionTypeAttr> subscriptionTypeAttrs;

	public SubscriptionTypeAttrFound(List<SubscriptionTypeAttr> subscriptionTypeAttrs) {
		this.subscriptionTypeAttrs = subscriptionTypeAttrs;
	}

	public List<SubscriptionTypeAttr> getSubscriptionTypeAttrs()	{
		return subscriptionTypeAttrs;
	}

}
