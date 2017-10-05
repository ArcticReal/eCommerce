package com.skytala.eCommerce.domain.orderStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderStatus.model.OrderStatus;
public class OrderStatusUpdated implements Event{

	private boolean success;

	public OrderStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
