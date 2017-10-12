package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model.ShipmentGatewayUps;
public class ShipmentGatewayUpsFound implements Event{

	private List<ShipmentGatewayUps> shipmentGatewayUpss;

	public ShipmentGatewayUpsFound(List<ShipmentGatewayUps> shipmentGatewayUpss) {
		this.shipmentGatewayUpss = shipmentGatewayUpss;
	}

	public List<ShipmentGatewayUps> getShipmentGatewayUpss()	{
		return shipmentGatewayUpss;
	}

}
