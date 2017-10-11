package com.skytala.eCommerce.domain.order.relations.orderItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
public class OrderItemBillingUpdated implements Event{

	private boolean success;

	public OrderItemBillingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
