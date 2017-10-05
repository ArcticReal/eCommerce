package com.skytala.eCommerce.domain.orderPaymentPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderPaymentPreference.model.OrderPaymentPreference;
public class OrderPaymentPreferenceUpdated implements Event{

	private boolean success;

	public OrderPaymentPreferenceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
