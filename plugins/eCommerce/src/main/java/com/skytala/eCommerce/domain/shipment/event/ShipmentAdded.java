package com.skytala.eCommerce.domain.shipment.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.model.Shipment;
public class ShipmentAdded implements Event{

	private Shipment addedShipment;
	private boolean success;

	public ShipmentAdded(Shipment addedShipment, boolean success){
		this.addedShipment = addedShipment;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Shipment getAddedShipment() {
		return addedShipment;
	}

}
