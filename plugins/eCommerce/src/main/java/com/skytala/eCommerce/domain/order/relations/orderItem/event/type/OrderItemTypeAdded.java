package com.skytala.eCommerce.domain.order.relations.orderItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;
public class OrderItemTypeAdded implements Event{

	private OrderItemType addedOrderItemType;
	private boolean success;

	public OrderItemTypeAdded(OrderItemType addedOrderItemType, boolean success){
		this.addedOrderItemType = addedOrderItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemType getAddedOrderItemType() {
		return addedOrderItemType;
	}

}
