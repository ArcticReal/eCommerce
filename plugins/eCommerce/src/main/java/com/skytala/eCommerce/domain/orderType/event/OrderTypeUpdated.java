package com.skytala.eCommerce.domain.orderType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderType.model.OrderType;
public class OrderTypeUpdated implements Event{

	private boolean success;

	public OrderTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
