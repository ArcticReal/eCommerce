package com.skytala.eCommerce.domain.order.relations.orderItem.event.group;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;
public class OrderItemGroupFound implements Event{

	private List<OrderItemGroup> orderItemGroups;

	public OrderItemGroupFound(List<OrderItemGroup> orderItemGroups) {
		this.orderItemGroups = orderItemGroups;
	}

	public List<OrderItemGroup> getOrderItemGroups()	{
		return orderItemGroups;
	}

}
