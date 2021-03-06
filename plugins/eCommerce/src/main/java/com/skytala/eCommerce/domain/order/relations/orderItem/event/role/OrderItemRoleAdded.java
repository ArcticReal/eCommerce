package com.skytala.eCommerce.domain.order.relations.orderItem.event.role;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.role.OrderItemRole;
public class OrderItemRoleAdded implements Event{

	private OrderItemRole addedOrderItemRole;
	private boolean success;

	public OrderItemRoleAdded(OrderItemRole addedOrderItemRole, boolean success){
		this.addedOrderItemRole = addedOrderItemRole;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public OrderItemRole getAddedOrderItemRole() {
		return addedOrderItemRole;
	}

}
