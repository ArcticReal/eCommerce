package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;
public class ShipmentStatusAdded implements Event{

	private ShipmentStatus addedShipmentStatus;
	private boolean success;

	public ShipmentStatusAdded(ShipmentStatus addedShipmentStatus, boolean success){
		this.addedShipmentStatus = addedShipmentStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentStatus getAddedShipmentStatus() {
		return addedShipmentStatus;
	}

}
