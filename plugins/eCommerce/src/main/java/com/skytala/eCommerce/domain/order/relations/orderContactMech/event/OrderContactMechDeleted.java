package com.skytala.eCommerce.domain.order.relations.orderContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderContactMech.model.OrderContactMech;
public class OrderContactMechDeleted implements Event{

	private boolean success;

	public OrderContactMechDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
