package com.skytala.eCommerce.domain.shipment.relations.shipmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentType.model.ShipmentType;
public class ShipmentTypeDeleted implements Event{

	private boolean success;

	public ShipmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
