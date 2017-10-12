package com.skytala.eCommerce.domain.shipment.relations.picklistItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklistItem.model.PicklistItem;
public class PicklistItemAdded implements Event{

	private PicklistItem addedPicklistItem;
	private boolean success;

	public PicklistItemAdded(PicklistItem addedPicklistItem, boolean success){
		this.addedPicklistItem = addedPicklistItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public PicklistItem getAddedPicklistItem() {
		return addedPicklistItem;
	}

}
