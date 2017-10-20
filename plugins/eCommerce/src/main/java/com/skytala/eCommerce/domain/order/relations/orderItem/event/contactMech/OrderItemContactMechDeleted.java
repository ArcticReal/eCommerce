package com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;
public class OrderItemContactMechDeleted implements Event{

	private boolean success;

	public OrderItemContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
