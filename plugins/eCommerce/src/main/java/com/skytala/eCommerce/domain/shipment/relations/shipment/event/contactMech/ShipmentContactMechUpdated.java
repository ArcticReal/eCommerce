package com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
public class ShipmentContactMechUpdated implements Event{

	private boolean success;

	public ShipmentContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
