package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;
public class InventoryItemStatusAdded implements Event{

	private InventoryItemStatus addedInventoryItemStatus;
	private boolean success;

	public InventoryItemStatusAdded(InventoryItemStatus addedInventoryItemStatus, boolean success){
		this.addedInventoryItemStatus = addedInventoryItemStatus;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemStatus getAddedInventoryItemStatus() {
		return addedInventoryItemStatus;
	}

}
