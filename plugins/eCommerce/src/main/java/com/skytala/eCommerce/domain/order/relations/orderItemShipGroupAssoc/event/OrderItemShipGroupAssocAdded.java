package com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.model.OrderItemShipGroupAssoc;
public class OrderItemShipGroupAssocAdded implements Event{

	private OrderItemShipGroupAssoc addedOrderItemShipGroupAssoc;
	private boolean success;

	public OrderItemShipGroupAssocAdded(OrderItemShipGroupAssoc addedOrderItemShipGroupAssoc, boolean success){
		this.addedOrderItemShipGroupAssoc = addedOrderItemShipGroupAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemShipGroupAssoc getAddedOrderItemShipGroupAssoc() {
		return addedOrderItemShipGroupAssoc;
	}

}
