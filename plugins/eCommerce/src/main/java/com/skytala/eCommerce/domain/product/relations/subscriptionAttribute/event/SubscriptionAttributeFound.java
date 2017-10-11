package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;
public class SubscriptionAttributeFound implements Event{

	private List<SubscriptionAttribute> subscriptionAttributes;

	public SubscriptionAttributeFound(List<SubscriptionAttribute> subscriptionAttributes) {
		this.subscriptionAttributes = subscriptionAttributes;
	}

	public List<SubscriptionAttribute> getSubscriptionAttributes()	{
		return subscriptionAttributes;
	}

}
