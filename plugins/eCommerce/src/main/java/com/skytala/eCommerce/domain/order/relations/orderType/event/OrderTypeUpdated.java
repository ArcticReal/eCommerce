package com.skytala.eCommerce.domain.order.relations.orderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;
public class OrderTypeUpdated implements Event{

	private boolean success;

	public OrderTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
