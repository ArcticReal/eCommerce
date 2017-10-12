package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.model.ShipmentContactMechType;
public class ShipmentContactMechTypeAdded implements Event{

	private ShipmentContactMechType addedShipmentContactMechType;
	private boolean success;

	public ShipmentContactMechTypeAdded(ShipmentContactMechType addedShipmentContactMechType, boolean success){
		this.addedShipmentContactMechType = addedShipmentContactMechType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentContactMechType getAddedShipmentContactMechType() {
		return addedShipmentContactMechType;
	}

}
