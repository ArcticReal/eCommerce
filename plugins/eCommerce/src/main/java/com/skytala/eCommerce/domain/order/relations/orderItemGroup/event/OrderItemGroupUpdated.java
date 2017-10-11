package com.skytala.eCommerce.domain.order.relations.orderItemGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemGroup.model.OrderItemGroup;
public class OrderItemGroupUpdated implements Event{

	private boolean success;

	public OrderItemGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
