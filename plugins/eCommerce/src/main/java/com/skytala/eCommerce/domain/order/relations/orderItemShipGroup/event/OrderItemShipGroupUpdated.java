package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model.OrderItemShipGroup;
public class OrderItemShipGroupUpdated implements Event{

	private boolean success;

	public OrderItemShipGroupUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
