package com.skytala.eCommerce.domain.shipment.relations.shipment.event.methodType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.methodType.ShipmentMethodType;
public class ShipmentMethodTypeUpdated implements Event{

	private boolean success;

	public ShipmentMethodTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
