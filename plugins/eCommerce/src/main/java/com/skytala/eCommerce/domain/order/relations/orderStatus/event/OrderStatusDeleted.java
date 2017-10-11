package com.skytala.eCommerce.domain.order.relations.orderStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderStatus.model.OrderStatus;
public class OrderStatusDeleted implements Event{

	private boolean success;

	public OrderStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
