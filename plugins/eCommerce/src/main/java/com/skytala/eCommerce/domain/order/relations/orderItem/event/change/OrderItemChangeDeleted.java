package com.skytala.eCommerce.domain.order.relations.orderItem.event.change;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.change.OrderItemChange;
public class OrderItemChangeDeleted implements Event{

	private boolean success;

	public OrderItemChangeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
