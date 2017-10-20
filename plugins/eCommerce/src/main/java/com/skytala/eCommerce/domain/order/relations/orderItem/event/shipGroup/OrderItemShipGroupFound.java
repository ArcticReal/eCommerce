package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroup;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroup.OrderItemShipGroup;
public class OrderItemShipGroupFound implements Event{

	private List<OrderItemShipGroup> orderItemShipGroups;

	public OrderItemShipGroupFound(List<OrderItemShipGroup> orderItemShipGroups) {
		this.orderItemShipGroups = orderItemShipGroups;
	}

	public List<OrderItemShipGroup> getOrderItemShipGroups()	{
		return orderItemShipGroups;
	}

}
