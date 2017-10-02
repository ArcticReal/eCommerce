package com.skytala.eCommerce.event;

import com.skytala.eCommerce.control.Event;
import com.skytala.eCommerce.entity.Subscription;

public class SubscriptionAdded implements Event {

	private Subscription addedSubscription;
	private boolean success;

	public SubscriptionAdded(Subscription addedSubscription, boolean success) {
		this.addedSubscription = addedSubscription;
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Subscription getAddedSubscription() {
		return addedSubscription;
	}

	public void setAddedSubscription(Subscription addedSubscription) {
		this.addedSubscription = addedSubscription;
	}
}
