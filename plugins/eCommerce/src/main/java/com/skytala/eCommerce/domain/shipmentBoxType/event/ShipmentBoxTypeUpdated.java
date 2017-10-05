package com.skytala.eCommerce.domain.shipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentBoxType.model.ShipmentBoxType;
public class ShipmentBoxTypeUpdated implements Event{

	private boolean success;

	public ShipmentBoxTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
