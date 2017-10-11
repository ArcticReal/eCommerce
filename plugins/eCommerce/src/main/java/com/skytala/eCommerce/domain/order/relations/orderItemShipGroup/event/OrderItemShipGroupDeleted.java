package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model.OrderItemShipGroup;
public class OrderItemShipGroupDeleted implements Event{

	private boolean success;

	public OrderItemShipGroupDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
