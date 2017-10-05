package com.skytala.eCommerce.domain.shipmentMethodType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentMethodType.model.ShipmentMethodType;
public class ShipmentMethodTypeUpdated implements Event{

	private boolean success;

	public ShipmentMethodTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
