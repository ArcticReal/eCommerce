package com.skytala.eCommerce.domain.shipment.relations.picklistItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistItem.model.PicklistItem;
public class PicklistItemUpdated implements Event{

	private boolean success;

	public PicklistItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
