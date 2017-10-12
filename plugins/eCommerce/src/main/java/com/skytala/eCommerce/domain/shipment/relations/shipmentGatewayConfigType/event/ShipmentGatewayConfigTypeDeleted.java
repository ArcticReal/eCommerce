package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.model.ShipmentGatewayConfigType;
public class ShipmentGatewayConfigTypeDeleted implements Event{

	private boolean success;

	public ShipmentGatewayConfigTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
