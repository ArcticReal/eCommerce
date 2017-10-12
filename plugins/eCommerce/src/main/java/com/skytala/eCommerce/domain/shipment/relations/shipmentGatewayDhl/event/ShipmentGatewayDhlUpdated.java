package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;
public class ShipmentGatewayDhlUpdated implements Event{

	private boolean success;

	public ShipmentGatewayDhlUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
