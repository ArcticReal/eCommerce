package com.skytala.eCommerce.domain.order.relations.returnItemShipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItemShipment.model.ReturnItemShipment;
public class ReturnItemShipmentUpdated implements Event{

	private boolean success;

	public ReturnItemShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
