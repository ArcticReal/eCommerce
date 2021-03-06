package com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
public class OrderItemAssocTypeAdded implements Event{

	private OrderItemAssocType addedOrderItemAssocType;
	private boolean success;

	public OrderItemAssocTypeAdded(OrderItemAssocType addedOrderItemAssocType, boolean success){
		this.addedOrderItemAssocType = addedOrderItemAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemAssocType getAddedOrderItemAssocType() {
		return addedOrderItemAssocType;
	}

}
