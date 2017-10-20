package com.skytala.eCommerce.domain.order.relations.orderItem.event.role;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.role.OrderItemRole;
public class OrderItemRoleFound implements Event{

	private List<OrderItemRole> orderItemRoles;

	public OrderItemRoleFound(List<OrderItemRole> orderItemRoles) {
		this.orderItemRoles = orderItemRoles;
	}

	public List<OrderItemRole> getOrderItemRoles()	{
		return orderItemRoles;
	}

}
