package com.skytala.eCommerce.domain.order.relations.orderItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
public class OrderItemDeleted implements Event{

	private boolean success;

	public OrderItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
