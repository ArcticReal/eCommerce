package com.skytala.eCommerce.domain.order.relations.orderHeader.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
public class OrderHeaderUpdated implements Event{

	private boolean success;

	public OrderHeaderUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
