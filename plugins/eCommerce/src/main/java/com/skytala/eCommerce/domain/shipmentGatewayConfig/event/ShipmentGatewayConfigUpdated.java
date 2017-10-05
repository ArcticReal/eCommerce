package com.skytala.eCommerce.domain.shipmentGatewayConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentGatewayConfig.model.ShipmentGatewayConfig;
public class ShipmentGatewayConfigUpdated implements Event{

	private boolean success;

	public ShipmentGatewayConfigUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
