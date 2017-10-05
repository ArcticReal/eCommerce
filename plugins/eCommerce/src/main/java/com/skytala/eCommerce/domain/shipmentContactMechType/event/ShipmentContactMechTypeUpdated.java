package com.skytala.eCommerce.domain.shipmentContactMechType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentContactMechType.model.ShipmentContactMechType;
public class ShipmentContactMechTypeUpdated implements Event{

	private boolean success;

	public ShipmentContactMechTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
