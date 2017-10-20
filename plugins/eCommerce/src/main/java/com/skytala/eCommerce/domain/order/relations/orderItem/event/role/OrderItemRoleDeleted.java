package com.skytala.eCommerce.domain.order.relations.orderItem.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.role.OrderItemRole;
public class OrderItemRoleDeleted implements Event{

	private boolean success;

	public OrderItemRoleDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
