package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl.ShipmentGatewayDhl;
public class ShipmentGatewayDhlAdded implements Event{

	private ShipmentGatewayDhl addedShipmentGatewayDhl;
	private boolean success;

	public ShipmentGatewayDhlAdded(ShipmentGatewayDhl addedShipmentGatewayDhl, boolean success){
		this.addedShipmentGatewayDhl = addedShipmentGatewayDhl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayDhl getAddedShipmentGatewayDhl() {
		return addedShipmentGatewayDhl;
	}

}
