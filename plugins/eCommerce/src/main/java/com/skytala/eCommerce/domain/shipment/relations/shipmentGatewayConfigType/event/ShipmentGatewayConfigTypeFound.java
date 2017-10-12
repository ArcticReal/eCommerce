package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.model.ShipmentGatewayConfigType;
public class ShipmentGatewayConfigTypeFound implements Event{

	private List<ShipmentGatewayConfigType> shipmentGatewayConfigTypes;

	public ShipmentGatewayConfigTypeFound(List<ShipmentGatewayConfigType> shipmentGatewayConfigTypes) {
		this.shipmentGatewayConfigTypes = shipmentGatewayConfigTypes;
	}

	public List<ShipmentGatewayConfigType> getShipmentGatewayConfigTypes()	{
		return shipmentGatewayConfigTypes;
	}

}
