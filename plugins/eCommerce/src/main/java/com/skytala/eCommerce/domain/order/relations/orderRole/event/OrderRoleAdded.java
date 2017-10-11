package com.skytala.eCommerce.domain.order.relations.orderRole.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;
public class OrderRoleAdded implements Event{

	private OrderRole addedOrderRole;
	private boolean success;

	public OrderRoleAdded(OrderRole addedOrderRole, boolean success){
		this.addedOrderRole = addedOrderRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderRole getAddedOrderRole() {
		return addedOrderRole;
	}

}
