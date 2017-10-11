package com.skytala.eCommerce.domain.order.relations.orderItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
public class OrderItemUpdated implements Event{

	private boolean success;

	public OrderItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
