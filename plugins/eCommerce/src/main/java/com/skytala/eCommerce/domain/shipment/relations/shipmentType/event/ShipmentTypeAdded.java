package com.skytala.eCommerce.domain.shipment.relations.shipmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentType.model.ShipmentType;
public class ShipmentTypeAdded implements Event{

	private ShipmentType addedShipmentType;
	private boolean success;

	public ShipmentTypeAdded(ShipmentType addedShipmentType, boolean success){
		this.addedShipmentType = addedShipmentType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentType getAddedShipmentType() {
		return addedShipmentType;
	}

}
