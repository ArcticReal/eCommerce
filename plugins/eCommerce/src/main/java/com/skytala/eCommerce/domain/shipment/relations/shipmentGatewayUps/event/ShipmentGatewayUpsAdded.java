package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model.ShipmentGatewayUps;
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
