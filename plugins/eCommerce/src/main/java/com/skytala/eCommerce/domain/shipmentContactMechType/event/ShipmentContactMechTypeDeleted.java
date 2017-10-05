package com.skytala.eCommerce.domain.shipmentContactMechType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentContactMechType.model.ShipmentContactMechType;
public class ShipmentContactMechTypeDeleted implements Event{

	private boolean success;

	public ShipmentContactMechTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
