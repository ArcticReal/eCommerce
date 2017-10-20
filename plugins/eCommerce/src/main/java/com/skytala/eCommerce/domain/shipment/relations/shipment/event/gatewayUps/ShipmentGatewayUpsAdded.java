package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;
public class ShipmentGatewayUpsAdded implements Event{

	private ShipmentGatewayUps addedShipmentGatewayUps;
	private boolean success;

	public ShipmentGatewayUpsAdded(ShipmentGatewayUps addedShipmentGatewayUps, boolean success){
		this.addedShipmentGatewayUps = addedShipmentGatewayUps;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayUps getAddedShipmentGatewayUps() {
		return addedShipmentGatewayUps;
	}

}
