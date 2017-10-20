package com.skytala.eCommerce.domain.order.relations.orderItem.event.group;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.group.OrderItemGroup;
public class OrderItemGroupAdded implements Event{

	private OrderItemGroup addedOrderItemGroup;
	private boolean success;

	public OrderItemGroupAdded(OrderItemGroup addedOrderItemGroup, boolean success){
		this.addedOrderItemGroup = addedOrderItemGroup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemGroup getAddedOrderItemGroup() {
		return addedOrderItemGroup;
	}

}
