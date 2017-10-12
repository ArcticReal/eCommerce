package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.model.ShipmentGatewayConfigType;
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
