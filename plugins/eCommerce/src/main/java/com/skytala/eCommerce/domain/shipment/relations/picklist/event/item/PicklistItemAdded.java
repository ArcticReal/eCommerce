package com.skytala.eCommerce.domain.shipment.relations.picklist.event.item;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;
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
