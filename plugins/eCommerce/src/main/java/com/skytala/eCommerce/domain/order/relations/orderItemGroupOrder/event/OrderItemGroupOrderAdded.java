package com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemGroupOrder.model.OrderItemGroupOrder;
public class OrderItemGroupOrderAdded implements Event{

	private OrderItemGroupOrder addedOrderItemGroupOrder;
	private boolean success;

	public OrderItemGroupOrderAdded(OrderItemGroupOrder addedOrderItemGroupOrder, boolean success){
		this.addedOrderItemGroupOrder = addedOrderItemGroupOrder;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemGroupOrder getAddedOrderItemGroupOrder() {
		return addedOrderItemGroupOrder;
	}

}
