package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelAppl.InventoryItemLabelAppl;
public class InventoryItemLabelApplDeleted implements Event{

	private boolean success;

	public InventoryItemLabelApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
