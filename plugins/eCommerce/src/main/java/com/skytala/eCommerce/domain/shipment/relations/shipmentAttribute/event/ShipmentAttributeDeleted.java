package com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentAttribute.model.ShipmentAttribute;
public class ShipmentAttributeDeleted implements Event{

	private boolean success;

	public ShipmentAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
