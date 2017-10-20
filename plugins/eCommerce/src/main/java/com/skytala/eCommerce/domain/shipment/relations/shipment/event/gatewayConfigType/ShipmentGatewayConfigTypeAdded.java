package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;
public class ShipmentGatewayConfigTypeAdded implements Event{

	private ShipmentGatewayConfigType addedShipmentGatewayConfigType;
	private boolean success;

	public ShipmentGatewayConfigTypeAdded(ShipmentGatewayConfigType addedShipmentGatewayConfigType, boolean success){
		this.addedShipmentGatewayConfigType = addedShipmentGatewayConfigType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayConfigType getAddedShipmentGatewayConfigType() {
		return addedShipmentGatewayConfigType;
	}

}
