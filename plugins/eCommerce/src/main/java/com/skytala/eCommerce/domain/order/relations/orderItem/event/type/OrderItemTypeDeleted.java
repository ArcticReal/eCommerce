package com.skytala.eCommerce.domain.order.relations.orderItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.type.OrderItemType;
public class OrderItemTypeDeleted implements Event{

	private boolean success;

	public OrderItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
