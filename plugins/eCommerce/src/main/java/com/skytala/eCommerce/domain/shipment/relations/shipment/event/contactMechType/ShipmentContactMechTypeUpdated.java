package com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMechType.ShipmentContactMechType;
public class ShipmentContactMechTypeUpdated implements Event{

	private boolean success;

	public ShipmentContactMechTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
