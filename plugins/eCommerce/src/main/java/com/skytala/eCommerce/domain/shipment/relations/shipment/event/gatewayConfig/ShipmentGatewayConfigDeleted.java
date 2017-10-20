package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;
public class ShipmentGatewayConfigDeleted implements Event{

	private boolean success;

	public ShipmentGatewayConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
