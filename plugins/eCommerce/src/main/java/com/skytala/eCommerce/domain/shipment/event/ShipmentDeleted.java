package com.skytala.eCommerce.domain.shipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.model.Shipment;
public class ShipmentDeleted implements Event{

	private boolean success;

	public ShipmentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
