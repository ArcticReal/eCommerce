package com.skytala.eCommerce.event;

import java.util.List;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Subscription;

public class SubscriptionFound implements Event {

	private List<Subscription> subscriptions;

	public SubscriptionFound(List<Subscription> subscriptions) {
		this.setSubscriptions(subscriptions);
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
