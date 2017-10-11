package com.skytala.eCommerce.domain.order.relations.orderItemContactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItemContactMech.model.OrderItemContactMech;
public class OrderItemContactMechFound implements Event{

	private List<OrderItemContactMech> orderItemContactMechs;

	public OrderItemContactMechFound(List<OrderItemContactMech> orderItemContactMechs) {
		this.orderItemContactMechs = orderItemContactMechs;
	}

	public List<OrderItemContactMech> getOrderItemContactMechs()	{
		return orderItemContactMechs;
	}

}
