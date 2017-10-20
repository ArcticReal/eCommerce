package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps.ShipmentGatewayUsps;
public class ShipmentGatewayUspsDeleted implements Event{

	private boolean success;

	public ShipmentGatewayUspsDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
