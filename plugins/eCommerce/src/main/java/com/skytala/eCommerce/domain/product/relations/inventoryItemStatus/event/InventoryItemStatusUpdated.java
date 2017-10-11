package com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.model.InventoryItemStatus;
public class InventoryItemStatusUpdated implements Event{

	private boolean success;

	public InventoryItemStatusUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
