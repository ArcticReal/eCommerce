package com.skytala.eCommerce.domain.order.relations.orderItem.event.assoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.assoc.OrderItemAssoc;
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
