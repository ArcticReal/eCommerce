package com.skytala.eCommerce.domain.shipment.relations.shipment.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.type.ShipmentType;
public class ShipmentTypeFound implements Event{

	private List<ShipmentType> shipmentTypes;

	public ShipmentTypeFound(List<ShipmentType> shipmentTypes) {
		this.shipmentTypes = shipmentTypes;
	}

	public List<ShipmentType> getShipmentTypes()	{
		return shipmentTypes;
	}

}
