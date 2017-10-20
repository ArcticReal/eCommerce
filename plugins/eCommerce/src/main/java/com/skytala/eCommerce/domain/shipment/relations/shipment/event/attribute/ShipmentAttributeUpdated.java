package com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;
public class ShipmentAttributeUpdated implements Event{

	private boolean success;

	public ShipmentAttributeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
