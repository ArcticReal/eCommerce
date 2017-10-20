package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type.InventoryItemType;
public class InventoryItemTypeUpdated implements Event{

	private boolean success;

	public InventoryItemTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
