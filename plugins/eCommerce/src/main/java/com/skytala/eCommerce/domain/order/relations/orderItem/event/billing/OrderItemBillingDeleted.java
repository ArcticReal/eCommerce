package com.skytala.eCommerce.domain.order.relations.orderItem.event.billing;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.billing.OrderItemBilling;
public class OrderItemBillingDeleted implements Event{

	private boolean success;

	public OrderItemBillingDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
