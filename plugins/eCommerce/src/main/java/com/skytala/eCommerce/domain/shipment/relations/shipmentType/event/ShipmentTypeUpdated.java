package com.skytala.eCommerce.domain.shipment.relations.shipmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentType.model.ShipmentType;
public class ShipmentTypeUpdated implements Event{

	private boolean success;

	public ShipmentTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
