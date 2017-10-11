package com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.model.OrderPaymentPreference;
public class OrderPaymentPreferenceAdded implements Event{

	private OrderPaymentPreference addedOrderPaymentPreference;
	private boolean success;

	public OrderPaymentPreferenceAdded(OrderPaymentPreference addedOrderPaymentPreference, boolean success){
		this.addedOrderPaymentPreference = addedOrderPaymentPreference;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderPaymentPreference getAddedOrderPaymentPreference() {
		return addedOrderPaymentPreference;
	}

}
