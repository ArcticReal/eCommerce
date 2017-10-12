package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMech.model.ShipmentContactMech;
public class ShipmentContactMechUpdated implements Event{

	private boolean success;

	public ShipmentContactMechUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
