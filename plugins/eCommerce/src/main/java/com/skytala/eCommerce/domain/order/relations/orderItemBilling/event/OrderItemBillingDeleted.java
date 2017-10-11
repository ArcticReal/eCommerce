package com.skytala.eCommerce.domain.order.relations.orderItemBilling.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemBilling.model.OrderItemBilling;
public class OrderItemBillingDeleted implements Event{

	private boolean success;

	public OrderItemBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
