package com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMechType.ShipmentContactMechType;
public class ShipmentContactMechTypeFound implements Event{

	private List<ShipmentContactMechType> shipmentContactMechTypes;

	public ShipmentContactMechTypeFound(List<ShipmentContactMechType> shipmentContactMechTypes) {
		this.shipmentContactMechTypes = shipmentContactMechTypes;
	}

	public List<ShipmentContactMechType> getShipmentContactMechTypes()	{
		return shipmentContactMechTypes;
	}

}
