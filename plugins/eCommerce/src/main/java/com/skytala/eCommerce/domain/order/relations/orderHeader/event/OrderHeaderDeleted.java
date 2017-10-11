package com.skytala.eCommerce.domain.order.relations.orderHeader.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
public class OrderHeaderDeleted implements Event{

	private boolean success;

	public OrderHeaderDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
