package com.skytala.eCommerce.domain.order.relations.orderItemContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemContactMech.model.OrderItemContactMech;
public class OrderItemContactMechUpdated implements Event{

	private boolean success;

	public OrderItemContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
