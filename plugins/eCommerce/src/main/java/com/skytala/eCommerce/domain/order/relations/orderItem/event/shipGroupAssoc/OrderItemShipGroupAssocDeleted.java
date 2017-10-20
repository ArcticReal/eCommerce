package com.skytala.eCommerce.domain.order.relations.orderItem.event.shipGroupAssoc;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.shipGroupAssoc.OrderItemShipGroupAssoc;
public class OrderItemShipGroupAssocDeleted implements Event{

	private boolean success;

	public OrderItemShipGroupAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
