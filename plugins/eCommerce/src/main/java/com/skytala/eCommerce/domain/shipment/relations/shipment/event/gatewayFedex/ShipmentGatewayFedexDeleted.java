package com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayFedex;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayFedex.ShipmentGatewayFedex;
public class ShipmentGatewayFedexDeleted implements Event{

	private boolean success;

	public ShipmentGatewayFedexDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
