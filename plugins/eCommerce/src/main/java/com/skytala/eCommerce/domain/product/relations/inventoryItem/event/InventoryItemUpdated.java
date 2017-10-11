package com.skytala.eCommerce.domain.product.relations.inventoryItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.InventoryItem;
public class InventoryItemUpdated implements Event{

	private boolean success;

	public InventoryItemUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
