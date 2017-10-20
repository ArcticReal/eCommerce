package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;
public class ShipmentGatewayUpsFound implements Event{

	private List<ShipmentGatewayUps> shipmentGatewayUpss;

	public ShipmentGatewayUpsFound(List<ShipmentGatewayUps> shipmentGatewayUpss) {
		this.shipmentGatewayUpss = shipmentGatewayUpss;
	}

	public List<ShipmentGatewayUps> getShipmentGatewayUpss()	{
		return shipmentGatewayUpss;
	}

}
