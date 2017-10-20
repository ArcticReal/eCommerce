package com.skytala.eCommerce.domain.order.relations.orderItem.event.contactMech;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderItem.model.contactMech.OrderItemContactMech;
public class OrderItemContactMechFound implements Event{

	private List<OrderItemContactMech> orderItemContactMechs;

	public OrderItemContactMechFound(List<OrderItemContactMech> orderItemContactMechs) {
		this.orderItemContactMechs = orderItemContactMechs;
	}

	public List<OrderItemContactMech> getOrderItemContactMechs()	{
		return orderItemContactMechs;
	}

}
