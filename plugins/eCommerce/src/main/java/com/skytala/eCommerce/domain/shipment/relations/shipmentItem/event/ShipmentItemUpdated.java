package com.skytala.eCommerce.domain.shipment.relations.shipmentItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.model.ShipmentItem;
public class ShipmentItemUpdated implements Event{

	private boolean success;

	public ShipmentItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
