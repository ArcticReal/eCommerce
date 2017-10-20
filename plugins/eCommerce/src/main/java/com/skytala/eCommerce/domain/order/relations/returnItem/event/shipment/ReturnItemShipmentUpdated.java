package com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
public class ReturnItemShipmentUpdated implements Event{

	private boolean success;

	public ReturnItemShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
