package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;
public class ShipmentGatewayDhlFound implements Event{

	private List<ShipmentGatewayDhl> shipmentGatewayDhls;

	public ShipmentGatewayDhlFound(List<ShipmentGatewayDhl> shipmentGatewayDhls) {
		this.shipmentGatewayDhls = shipmentGatewayDhls;
	}

	public List<ShipmentGatewayDhl> getShipmentGatewayDhls()	{
		return shipmentGatewayDhls;
	}

}
