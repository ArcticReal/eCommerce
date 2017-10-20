package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;
public class ShipmentGatewayFedexAdded implements Event{

	private ShipmentGatewayFedex addedShipmentGatewayFedex;
	private boolean success;

	public ShipmentGatewayFedexAdded(ShipmentGatewayFedex addedShipmentGatewayFedex, boolean success){
		this.addedShipmentGatewayFedex = addedShipmentGatewayFedex;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayFedex getAddedShipmentGatewayFedex() {
		return addedShipmentGatewayFedex;
	}

}
