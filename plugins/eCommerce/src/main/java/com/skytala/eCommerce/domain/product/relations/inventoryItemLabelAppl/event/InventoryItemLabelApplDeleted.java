package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model.InventoryItemLabelAppl;
public class InventoryItemLabelApplDeleted implements Event{

	private boolean success;

	public InventoryItemLabelApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
