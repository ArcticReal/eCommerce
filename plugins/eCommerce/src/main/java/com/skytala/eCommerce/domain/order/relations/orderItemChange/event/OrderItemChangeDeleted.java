package com.skytala.eCommerce.domain.order.relations.orderItemChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemChange.model.OrderItemChange;
public class OrderItemChangeDeleted implements Event{

	private boolean success;

	public OrderItemChangeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
