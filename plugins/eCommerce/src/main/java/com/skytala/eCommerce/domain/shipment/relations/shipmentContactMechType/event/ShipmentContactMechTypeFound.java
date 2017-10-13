package com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentContactMechType.model.ShipmentContactMechType;
public class ShipmentContactMechTypeFound implements Event{

	private List<ShipmentContactMechType> shipmentContactMechTypes;

	public ShipmentContactMechTypeFound(List<ShipmentContactMechType> shipmentContactMechTypes) {
		this.shipmentContactMechTypes = shipmentContactMechTypes;
	}

	public List<ShipmentContactMechType> getShipmentContactMechTypes()	{
		return shipmentContactMechTypes;
	}

}