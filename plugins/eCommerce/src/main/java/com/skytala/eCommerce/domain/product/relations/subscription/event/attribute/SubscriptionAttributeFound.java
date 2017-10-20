package com.skytala.eCommerce.domain.product.relations.subscription.event.attribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.subscription.model.attribute.SubscriptionAttribute;
public class SubscriptionAttributeFound implements Event{

	private List<SubscriptionAttribute> subscriptionAttributes;

	public SubscriptionAttributeFound(List<SubscriptionAttribute> subscriptionAttributes) {
		this.subscriptionAttributes = subscriptionAttributes;
	}

	public List<SubscriptionAttribute> getSubscriptionAttributes()	{
		return subscriptionAttributes;
	}

}
