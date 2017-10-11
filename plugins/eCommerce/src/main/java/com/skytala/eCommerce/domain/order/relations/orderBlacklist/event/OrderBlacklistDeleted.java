package com.skytala.eCommerce.domain.order.relations.orderBlacklist.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
public class OrderBlacklistDeleted implements Event{

	private boolean success;

	public OrderBlacklistDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
