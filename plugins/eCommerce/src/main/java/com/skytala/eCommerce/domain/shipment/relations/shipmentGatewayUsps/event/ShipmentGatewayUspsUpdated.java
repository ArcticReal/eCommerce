package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.model.ShipmentGatewayUsps;
public class ShipmentGatewayUspsUpdated implements Event{

	private boolean success;

	public ShipmentGatewayUspsUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
