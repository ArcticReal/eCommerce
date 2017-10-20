package com.skytala.eCommerce.domain.shipment.relations.shipment.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.type.ShipmentType;
public class ShipmentTypeDeleted implements Event{

	private boolean success;

	public ShipmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
