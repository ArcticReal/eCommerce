package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model.ShipmentGatewayUps;
public class ShipmentGatewayUpsUpdated implements Event{

	private boolean success;

	public ShipmentGatewayUpsUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
