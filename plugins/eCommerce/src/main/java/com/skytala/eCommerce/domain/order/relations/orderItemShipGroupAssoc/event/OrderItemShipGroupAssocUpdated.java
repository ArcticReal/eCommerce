package com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemShipGroupAssoc.model.OrderItemShipGroupAssoc;
public class OrderItemShipGroupAssocUpdated implements Event{

	private boolean success;

	public OrderItemShipGroupAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
