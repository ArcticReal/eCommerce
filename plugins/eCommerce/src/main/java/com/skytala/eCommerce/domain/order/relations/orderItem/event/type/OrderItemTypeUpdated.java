package com.skytala.eCommerce.domain.order.relations.orderItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;
public class OrderItemTypeUpdated implements Event{

	private boolean success;

	public OrderItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
