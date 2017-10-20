package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.labelAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.labelAppl.InventoryItemLabelAppl;
public class InventoryItemLabelApplUpdated implements Event{

	private boolean success;

	public InventoryItemLabelApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
