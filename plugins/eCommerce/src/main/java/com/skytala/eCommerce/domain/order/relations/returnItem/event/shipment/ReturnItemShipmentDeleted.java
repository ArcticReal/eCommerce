package com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
public class ReturnItemShipmentDeleted implements Event{

	private boolean success;

	public ReturnItemShipmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
