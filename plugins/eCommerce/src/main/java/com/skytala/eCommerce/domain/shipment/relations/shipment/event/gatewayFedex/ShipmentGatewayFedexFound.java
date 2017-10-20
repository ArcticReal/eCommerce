package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;
public class ShipmentGatewayFedexFound implements Event{

	private List<ShipmentGatewayFedex> shipmentGatewayFedexs;

	public ShipmentGatewayFedexFound(List<ShipmentGatewayFedex> shipmentGatewayFedexs) {
		this.shipmentGatewayFedexs = shipmentGatewayFedexs;
	}

	public List<ShipmentGatewayFedex> getShipmentGatewayFedexs()	{
		return shipmentGatewayFedexs;
	}

}
