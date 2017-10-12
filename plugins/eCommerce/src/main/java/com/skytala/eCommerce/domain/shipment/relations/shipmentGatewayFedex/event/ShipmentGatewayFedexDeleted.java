package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;
public class ShipmentGatewayFedexDeleted implements Event{

	private boolean success;

	public ShipmentGatewayFedexDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
