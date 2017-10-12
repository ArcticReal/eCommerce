package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUps.model.ShipmentGatewayUps;
public class ShipmentGatewayUpsDeleted implements Event{

	private boolean success;

	public ShipmentGatewayUpsDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
