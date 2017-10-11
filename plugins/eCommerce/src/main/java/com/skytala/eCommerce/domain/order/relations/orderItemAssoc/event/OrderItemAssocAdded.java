package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;
public class OrderItemAssocAdded implements Event{

	private OrderItemAssoc addedOrderItemAssoc;
	private boolean success;

	public OrderItemAssocAdded(OrderItemAssoc addedOrderItemAssoc, boolean success){
		this.addedOrderItemAssoc = addedOrderItemAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemAssoc getAddedOrderItemAssoc() {
		return addedOrderItemAssoc;
	}

}
