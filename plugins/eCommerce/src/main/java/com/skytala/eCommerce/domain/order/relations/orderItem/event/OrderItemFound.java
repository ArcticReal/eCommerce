package com.skytala.eCommerce.domain.order.relations.orderItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
public class OrderItemFound implements Event{

	private List<OrderItem> orderItems;

	public OrderItemFound(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderItem> getOrderItems()	{
		return orderItems;
	}

}
