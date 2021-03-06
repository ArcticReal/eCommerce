package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
public class OrderItemShipGroupAdded implements Event{

	private OrderItemShipGroup addedOrderItemShipGroup;
	private boolean success;

	public OrderItemShipGroupAdded(OrderItemShipGroup addedOrderItemShipGroup, boolean success){
		this.addedOrderItemShipGroup = addedOrderItemShipGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemShipGroup getAddedOrderItemShipGroup() {
		return addedOrderItemShipGroup;
	}

}
