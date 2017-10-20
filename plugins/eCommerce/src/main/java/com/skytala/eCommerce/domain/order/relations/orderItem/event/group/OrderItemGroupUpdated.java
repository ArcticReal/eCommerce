package com.skytala.eCommerce.domain.order.relations.orderItem.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;
public class OrderItemGroupUpdated implements Event{

	private boolean success;

	public OrderItemGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
