package com.skytala.eCommerce.domain.orderBlacklistType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.orderBlacklistType.model.OrderBlacklistType;
public class OrderBlacklistTypeDeleted implements Event{

	private boolean success;

	public OrderBlacklistTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
