package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;
public class ShipmentGatewayFedexUpdated implements Event{

	private boolean success;

	public ShipmentGatewayFedexUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
