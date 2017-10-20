package com.skytala.eCommerce.domain.shipment.relations.shipment.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipment.model.item.ShipmentItem;
public class ShipmentItemDeleted implements Event{

	private boolean success;

	public ShipmentItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
