package com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType.ShipmentMethodType;
public class ShipmentMethodTypeFound implements Event{

	private List<ShipmentMethodType> shipmentMethodTypes;

	public ShipmentMethodTypeFound(List<ShipmentMethodType> shipmentMethodTypes) {
		this.shipmentMethodTypes = shipmentMethodTypes;
	}

	public List<ShipmentMethodType> getShipmentMethodTypes()	{
		return shipmentMethodTypes;
	}

}
