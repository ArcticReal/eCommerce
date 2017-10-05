package com.skytala.eCommerce.domain.shipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.model.Shipment;
public class ShipmentUpdated implements Event{

	private boolean success;

	public ShipmentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
