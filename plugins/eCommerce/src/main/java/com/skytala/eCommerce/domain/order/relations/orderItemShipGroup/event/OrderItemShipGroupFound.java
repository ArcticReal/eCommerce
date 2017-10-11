package com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGroup.model.OrderItemShipGroup;
public class OrderItemShipGroupFound implements Event{

	private List<OrderItemShipGroup> orderItemShipGroups;

	public OrderItemShipGroupFound(List<OrderItemShipGroup> orderItemShipGroups) {
		this.orderItemShipGroups = orderItemShipGroups;
	}

	public List<OrderItemShipGroup> getOrderItemShipGroups()	{
		return orderItemShipGroups;
	}

}
