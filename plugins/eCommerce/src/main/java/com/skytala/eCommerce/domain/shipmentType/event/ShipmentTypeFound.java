package com.skytala.eCommerce.domain.shipmentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentType.model.ShipmentType;
public class ShipmentTypeFound implements Event{

	private List<ShipmentType> shipmentTypes;

	public ShipmentTypeFound(List<ShipmentType> shipmentTypes) {
		this.shipmentTypes = shipmentTypes;
	}

	public List<ShipmentType> getShipmentTypes()	{
		return shipmentTypes;
	}

}
