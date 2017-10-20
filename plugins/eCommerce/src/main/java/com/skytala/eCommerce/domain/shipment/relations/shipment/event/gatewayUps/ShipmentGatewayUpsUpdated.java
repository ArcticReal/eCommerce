package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;
public class ShipmentGatewayUpsUpdated implements Event{

	private boolean success;

	public ShipmentGatewayUpsUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
