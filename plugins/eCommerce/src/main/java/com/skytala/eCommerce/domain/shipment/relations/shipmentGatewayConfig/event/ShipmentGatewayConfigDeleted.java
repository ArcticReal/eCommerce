package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.model.ShipmentGatewayConfig;
public class ShipmentGatewayConfigDeleted implements Event{

	private boolean success;

	public ShipmentGatewayConfigDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
