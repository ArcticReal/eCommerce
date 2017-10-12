package com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.model.ShipmentMethodType;
public class ShipmentMethodTypeFound implements Event{

	private List<ShipmentMethodType> shipmentMethodTypes;

	public ShipmentMethodTypeFound(List<ShipmentMethodType> shipmentMethodTypes) {
		this.shipmentMethodTypes = shipmentMethodTypes;
	}

	public List<ShipmentMethodType> getShipmentMethodTypes()	{
		return shipmentMethodTypes;
	}

}
