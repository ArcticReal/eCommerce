package com.skytala.eCommerce.domain.order.relations.orderTerm.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
public class OrderTermUpdated implements Event{

	private boolean success;

	public OrderTermUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
