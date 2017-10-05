package com.skytala.eCommerce.domain.inventoryItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItem.model.InventoryItem;
public class InventoryItemAdded implements Event{

	private InventoryItem addedInventoryItem;
	private boolean success;

	public InventoryItemAdded(InventoryItem addedInventoryItem, boolean success){
		this.addedInventoryItem = addedInventoryItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItem getAddedInventoryItem() {
		return addedInventoryItem;
	}

}
