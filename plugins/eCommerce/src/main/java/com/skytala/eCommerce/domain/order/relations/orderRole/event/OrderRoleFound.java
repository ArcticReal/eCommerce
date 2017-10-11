package com.skytala.eCommerce.domain.order.relations.orderRole.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderRole.model.OrderRole;
public class OrderRoleFound implements Event{

	private List<OrderRole> orderRoles;

	public OrderRoleFound(List<OrderRole> orderRoles) {
		this.orderRoles = orderRoles;
	}

	public List<OrderRole> getOrderRoles()	{
		return orderRoles;
	}

}
