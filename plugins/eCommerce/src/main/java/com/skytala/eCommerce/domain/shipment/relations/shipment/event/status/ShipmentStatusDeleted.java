package com.skytala.eCommerce.domain.shipment.relations.shipment.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.status.ShipmentStatus;
public class ShipmentStatusDeleted implements Event{

	private boolean success;

	public ShipmentStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
