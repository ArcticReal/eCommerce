package com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.groupOrder.OrderItemGroupOrder;
public class OrderItemGroupOrderUpdated implements Event{

	private boolean success;

	public OrderItemGroupOrderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
