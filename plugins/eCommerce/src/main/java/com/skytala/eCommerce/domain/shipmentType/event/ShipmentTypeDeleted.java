package com.skytala.eCommerce.domain.shipmentType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipmentType.model.ShipmentType;
public class ShipmentTypeDeleted implements Event{

	private boolean success;

	public ShipmentTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
