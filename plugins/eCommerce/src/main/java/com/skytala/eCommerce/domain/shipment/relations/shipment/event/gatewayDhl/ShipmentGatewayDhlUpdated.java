package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayDhl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayDhl.ShipmentGatewayDhl;
public class ShipmentGatewayDhlUpdated implements Event{

	private boolean success;

	public ShipmentGatewayDhlUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
