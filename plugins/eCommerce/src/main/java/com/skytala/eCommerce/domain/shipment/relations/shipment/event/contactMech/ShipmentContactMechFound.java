package com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
public class ShipmentContactMechFound implements Event{

	private List<ShipmentContactMech> shipmentContactMechs;

	public ShipmentContactMechFound(List<ShipmentContactMech> shipmentContactMechs) {
		this.shipmentContactMechs = shipmentContactMechs;
	}

	public List<ShipmentContactMech> getShipmentContactMechs()	{
		return shipmentContactMechs;
	}

}
