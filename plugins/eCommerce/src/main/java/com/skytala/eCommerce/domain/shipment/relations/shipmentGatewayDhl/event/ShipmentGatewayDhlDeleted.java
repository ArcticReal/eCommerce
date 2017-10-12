package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;
public class ShipmentGatewayDhlDeleted implements Event{

	private boolean success;

	public ShipmentGatewayDhlDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
