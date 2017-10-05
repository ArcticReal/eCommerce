package com.skytala.eCommerce.domain.shipmentBoxType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentBoxType.model.ShipmentBoxType;
public class ShipmentBoxTypeDeleted implements Event{

	private boolean success;

	public ShipmentBoxTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
