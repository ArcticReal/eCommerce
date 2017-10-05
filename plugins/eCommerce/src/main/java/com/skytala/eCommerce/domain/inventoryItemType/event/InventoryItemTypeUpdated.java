package com.skytala.eCommerce.domain.inventoryItemType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemType.model.InventoryItemType;
public class InventoryItemTypeUpdated implements Event{

	private boolean success;

	public InventoryItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
