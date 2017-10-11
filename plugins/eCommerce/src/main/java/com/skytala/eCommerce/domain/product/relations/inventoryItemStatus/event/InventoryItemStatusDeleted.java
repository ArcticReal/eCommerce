package com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.model.InventoryItemStatus;
public class InventoryItemStatusDeleted implements Event{

	private boolean success;

	public InventoryItemStatusDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
