package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;
public class ShipmentGatewayFedexFound implements Event{

	private List<ShipmentGatewayFedex> shipmentGatewayFedexs;

	public ShipmentGatewayFedexFound(List<ShipmentGatewayFedex> shipmentGatewayFedexs) {
		this.shipmentGatewayFedexs = shipmentGatewayFedexs;
	}

	public List<ShipmentGatewayFedex> getShipmentGatewayFedexs()	{
		return shipmentGatewayFedexs;
	}

}
