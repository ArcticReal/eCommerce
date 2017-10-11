package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;
public class OrderItemAssocUpdated implements Event{

	private boolean success;

	public OrderItemAssocUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
