package com.skytala.eCommerce.domain.order.relations.orderItemAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemAssoc.model.OrderItemAssoc;
public class OrderItemAssocDeleted implements Event{

	private boolean success;

	public OrderItemAssocDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
