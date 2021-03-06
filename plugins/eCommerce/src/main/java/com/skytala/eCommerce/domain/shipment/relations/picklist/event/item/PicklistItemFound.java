package com.skytala.eCommerce.domain.shipment.relations.picklist.event.item;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;
public class PicklistItemFound implements Event{

	private List<PicklistItem> picklistItems;

	public PicklistItemFound(List<PicklistItem> picklistItems) {
		this.picklistItems = picklistItems;
	}

	public List<PicklistItem> getPicklistItems()	{
		return picklistItems;
	}

}
