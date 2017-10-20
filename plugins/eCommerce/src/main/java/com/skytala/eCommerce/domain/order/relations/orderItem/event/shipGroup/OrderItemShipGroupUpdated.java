package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
public class OrderItemShipGroupUpdated implements Event{

	private boolean success;

	public OrderItemShipGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
