package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.detail.InventoryItemDetail;
public class InventoryItemDetailDeleted implements Event{

	private boolean success;

	public InventoryItemDetailDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
