package com.skytala.eCommerce.domain.inventoryItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemType.model.InventoryItemType;
public class InventoryItemTypeAdded implements Event{

	private InventoryItemType addedInventoryItemType;
	private boolean success;

	public InventoryItemTypeAdded(InventoryItemType addedInventoryItemType, boolean success){
		this.addedInventoryItemType = addedInventoryItemType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemType getAddedInventoryItemType() {
		return addedInventoryItemType;
	}

}
