package com.skytala.eCommerce.domain.shipment.relations.shipment.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;
public class ShipmentItemUpdated implements Event{

	private boolean success;

	public ShipmentItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
