package com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.model.ShipmentBoxType;
public class ShipmentBoxTypeFound implements Event{

	private List<ShipmentBoxType> shipmentBoxTypes;

	public ShipmentBoxTypeFound(List<ShipmentBoxType> shipmentBoxTypes) {
		this.shipmentBoxTypes = shipmentBoxTypes;
	}

	public List<ShipmentBoxType> getShipmentBoxTypes()	{
		return shipmentBoxTypes;
	}

}
