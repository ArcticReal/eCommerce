package com.skytala.eCommerce.domain.order.relations.orderItem.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.billing.OrderItemBilling;
public class OrderItemBillingUpdated implements Event{

	private boolean success;

	public OrderItemBillingUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
