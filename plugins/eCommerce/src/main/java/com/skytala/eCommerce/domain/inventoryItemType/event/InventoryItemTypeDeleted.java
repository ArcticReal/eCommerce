package com.skytala.eCommerce.domain.inventoryItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemType.model.InventoryItemType;
public class InventoryItemTypeDeleted implements Event{

	private boolean success;

	public InventoryItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
