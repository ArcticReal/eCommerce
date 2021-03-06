package com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderPaymentPreference.model.OrderPaymentPreference;
public class OrderPaymentPreferenceFound implements Event{

	private List<OrderPaymentPreference> orderPaymentPreferences;

	public OrderPaymentPreferenceFound(List<OrderPaymentPreference> orderPaymentPreferences) {
		this.orderPaymentPreferences = orderPaymentPreferences;
	}

	public List<OrderPaymentPreference> getOrderPaymentPreferences()	{
		return orderPaymentPreferences;
	}

}
