package com.skytala.eCommerce.domain.order.relations.orderShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.orderShipment.model.OrderShipment;
public class OrderShipmentDeleted implements Event{

	private boolean success;

	public OrderShipmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
