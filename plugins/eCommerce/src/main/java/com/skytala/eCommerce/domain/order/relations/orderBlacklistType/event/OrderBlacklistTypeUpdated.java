package com.skytala.eCommerce.domain.order.relations.orderBlacklistType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklistType.model.OrderBlacklistType;
public class OrderBlacklistTypeUpdated implements Event{

	private boolean success;

	public OrderBlacklistTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
