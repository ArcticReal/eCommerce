package com.skytala.eCommerce.domain.order.relations.orderItemChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemChange.model.OrderItemChange;
public class OrderItemChangeUpdated implements Event{

	private boolean success;

	public OrderItemChangeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
