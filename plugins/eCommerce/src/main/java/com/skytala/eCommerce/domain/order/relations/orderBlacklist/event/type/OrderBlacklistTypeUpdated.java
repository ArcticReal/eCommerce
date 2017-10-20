package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;
public class OrderBlacklistTypeUpdated implements Event{

	private boolean success;

	public OrderBlacklistTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
