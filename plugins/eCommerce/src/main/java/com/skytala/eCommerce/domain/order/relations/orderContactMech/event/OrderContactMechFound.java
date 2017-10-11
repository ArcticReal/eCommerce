package com.skytala.eCommerce.domain.order.relations.orderContactMech.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
public class OrderContactMechFound implements Event{

	private List<OrderContactMech> orderContactMechs;

	public OrderContactMechFound(List<OrderContactMech> orderContactMechs) {
		this.orderContactMechs = orderContactMechs;
	}

	public List<OrderContactMech> getOrderContactMechs()	{
		return orderContactMechs;
	}

}
