package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps.ShipmentGatewayUsps;
public class ShipmentGatewayUspsAdded implements Event{

	private ShipmentGatewayUsps addedShipmentGatewayUsps;
	private boolean success;

	public ShipmentGatewayUspsAdded(ShipmentGatewayUsps addedShipmentGatewayUsps, boolean success){
		this.addedShipmentGatewayUsps = addedShipmentGatewayUsps;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayUsps getAddedShipmentGatewayUsps() {
		return addedShipmentGatewayUsps;
	}

}
