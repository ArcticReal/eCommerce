package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;
public class ShipmentStatusDeleted implements Event{

	private boolean success;

	public ShipmentStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
