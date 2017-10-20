package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;
public class ShipmentGatewayConfigAdded implements Event{

	private ShipmentGatewayConfig addedShipmentGatewayConfig;
	private boolean success;

	public ShipmentGatewayConfigAdded(ShipmentGatewayConfig addedShipmentGatewayConfig, boolean success){
		this.addedShipmentGatewayConfig = addedShipmentGatewayConfig;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShipmentGatewayConfig getAddedShipmentGatewayConfig() {
		return addedShipmentGatewayConfig;
	}

}
