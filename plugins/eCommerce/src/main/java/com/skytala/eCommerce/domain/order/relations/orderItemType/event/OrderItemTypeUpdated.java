package com.skytala.eCommerce.domain.order.relations.orderItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemType.model.OrderItemType;
public class OrderItemTypeUpdated implements Event{

	private boolean success;

	public OrderItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
