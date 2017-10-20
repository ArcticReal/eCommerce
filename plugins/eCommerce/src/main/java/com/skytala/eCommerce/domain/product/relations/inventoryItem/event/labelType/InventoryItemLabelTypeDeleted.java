package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelType.InventoryItemLabelType;
public class InventoryItemLabelTypeDeleted implements Event{

	private boolean success;

	public InventoryItemLabelTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
