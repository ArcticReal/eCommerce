package com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
public class ShipmentContactMechAdded implements Event{

	private ShipmentContactMech addedShipmentContactMech;
	private boolean success;

	public ShipmentContactMechAdded(ShipmentContactMech addedShipmentContactMech, boolean success){
		this.addedShipmentContactMech = addedShipmentContactMech;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentContactMech getAddedShipmentContactMech() {
		return addedShipmentContactMech;
	}

}
