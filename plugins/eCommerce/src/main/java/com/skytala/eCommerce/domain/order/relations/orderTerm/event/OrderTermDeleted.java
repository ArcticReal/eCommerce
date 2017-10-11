package com.skytala.eCommerce.domain.order.relations.orderTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
public class OrderTermDeleted implements Event{

	private boolean success;

	public OrderTermDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
