package com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.model.ShipmentBoxType;
public class ShipmentBoxTypeAdded implements Event{

	private ShipmentBoxType addedShipmentBoxType;
	private boolean success;

	public ShipmentBoxTypeAdded(ShipmentBoxType addedShipmentBoxType, boolean success){
		this.addedShipmentBoxType = addedShipmentBoxType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentBoxType getAddedShipmentBoxType() {
		return addedShipmentBoxType;
	}

}
