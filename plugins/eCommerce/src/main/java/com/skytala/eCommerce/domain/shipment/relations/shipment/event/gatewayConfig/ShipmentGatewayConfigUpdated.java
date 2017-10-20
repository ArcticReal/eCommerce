package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;
public class ShipmentGatewayConfigUpdated implements Event{

	private boolean success;

	public ShipmentGatewayConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
