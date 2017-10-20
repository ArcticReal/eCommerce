package com.skytala.eCommerce.domain.shipment.relations.picklist.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;
public class PicklistItemDeleted implements Event{

	private boolean success;

	public PicklistItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
