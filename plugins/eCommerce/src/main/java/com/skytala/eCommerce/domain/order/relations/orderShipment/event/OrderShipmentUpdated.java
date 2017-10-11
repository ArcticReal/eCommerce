package com.skytala.eCommerce.domain.order.relations.orderShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
public class OrderShipmentUpdated implements Event{

	private boolean success;

	public OrderShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
