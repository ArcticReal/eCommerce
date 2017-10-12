package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.model.ShipmentGatewayConfig;
public class ShipmentGatewayConfigUpdated implements Event{

	private boolean success;

	public ShipmentGatewayConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
