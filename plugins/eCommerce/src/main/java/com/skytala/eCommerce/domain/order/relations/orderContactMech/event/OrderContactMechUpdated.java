package com.skytala.eCommerce.domain.order.relations.orderContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
public class OrderContactMechUpdated implements Event{

	private boolean success;

	public OrderContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
