package com.skytala.eCommerce.domain.order.relations.orderItem.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;
public class OrderItemTypeFound implements Event{

	private List<OrderItemType> orderItemTypes;

	public OrderItemTypeFound(List<OrderItemType> orderItemTypes) {
		this.orderItemTypes = orderItemTypes;
	}

	public List<OrderItemType> getOrderItemTypes()	{
		return orderItemTypes;
	}

}
