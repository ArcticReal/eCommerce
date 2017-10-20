package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.type.OrderBlacklistType;
public class OrderBlacklistTypeDeleted implements Event{

	private boolean success;

	public OrderBlacklistTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
