package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;
public class ShipmentGatewayConfigTypeDeleted implements Event{

	private boolean success;

	public ShipmentGatewayConfigTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
