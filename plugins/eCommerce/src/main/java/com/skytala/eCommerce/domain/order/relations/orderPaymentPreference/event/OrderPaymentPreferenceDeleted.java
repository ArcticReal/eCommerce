package com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.model.OrderPaymentPreference;
public class OrderPaymentPreferenceDeleted implements Event{

	private boolean success;

	public OrderPaymentPreferenceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
