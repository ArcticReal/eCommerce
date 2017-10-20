package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type.InventoryItemType;
public class InventoryItemTypeDeleted implements Event{

	private boolean success;

	public InventoryItemTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
