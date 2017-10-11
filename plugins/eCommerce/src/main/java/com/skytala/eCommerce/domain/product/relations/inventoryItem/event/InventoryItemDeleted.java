package com.skytala.eCommerce.domain.product.relations.inventoryItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.InventoryItem;
public class InventoryItemDeleted implements Event{

	private boolean success;

	public InventoryItemDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
