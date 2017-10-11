package com.skytala.eCommerce.domain.order.relations.orderItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
public class OrderItemAdded implements Event{

	private OrderItem addedOrderItem;
	private boolean success;

	public OrderItemAdded(OrderItem addedOrderItem, boolean success){
		this.addedOrderItem = addedOrderItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItem getAddedOrderItem() {
		return addedOrderItem;
	}

}
