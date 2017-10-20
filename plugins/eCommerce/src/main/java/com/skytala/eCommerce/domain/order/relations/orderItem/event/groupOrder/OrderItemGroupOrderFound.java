package com.skytala.eCommerce.domain.order.relations.orderItem.event.groupOrder;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.groupOrder.OrderItemGroupOrder;
public class OrderItemGroupOrderFound implements Event{

	private List<OrderItemGroupOrder> orderItemGroupOrders;

	public OrderItemGroupOrderFound(List<OrderItemGroupOrder> orderItemGroupOrders) {
		this.orderItemGroupOrders = orderItemGroupOrders;
	}

	public List<OrderItemGroupOrder> getOrderItemGroupOrders()	{
		return orderItemGroupOrders;
	}

}
