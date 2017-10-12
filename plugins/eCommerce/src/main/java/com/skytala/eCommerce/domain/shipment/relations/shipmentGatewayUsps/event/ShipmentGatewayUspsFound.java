package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.model.ShipmentGatewayUsps;
public class ShipmentGatewayUspsFound implements Event{

	private List<ShipmentGatewayUsps> shipmentGatewayUspss;

	public ShipmentGatewayUspsFound(List<ShipmentGatewayUsps> shipmentGatewayUspss) {
		this.shipmentGatewayUspss = shipmentGatewayUspss;
	}

	public List<ShipmentGatewayUsps> getShipmentGatewayUspss()	{
		return shipmentGatewayUspss;
	}

}
