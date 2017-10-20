package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;
public class InventoryItemLabelUpdated implements Event{

	private boolean success;

	public InventoryItemLabelUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
