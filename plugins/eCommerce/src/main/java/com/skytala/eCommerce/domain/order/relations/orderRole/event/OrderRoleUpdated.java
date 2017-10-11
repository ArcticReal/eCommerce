package com.skytala.eCommerce.domain.order.relations.orderRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;
public class OrderRoleUpdated implements Event{

	private boolean success;

	public OrderRoleUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
