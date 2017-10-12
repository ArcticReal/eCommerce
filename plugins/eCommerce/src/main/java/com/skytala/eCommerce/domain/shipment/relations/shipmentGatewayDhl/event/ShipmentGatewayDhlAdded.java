package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;
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
