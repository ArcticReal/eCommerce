package com.skytala.eCommerce.domain.order.relations.orderItemRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemRole.model.OrderItemRole;
public class OrderItemRoleDeleted implements Event{

	private boolean success;

	public OrderItemRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
