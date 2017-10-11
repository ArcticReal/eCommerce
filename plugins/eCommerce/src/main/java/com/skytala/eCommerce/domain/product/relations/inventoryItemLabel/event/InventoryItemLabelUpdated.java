package com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.model.InventoryItemLabel;
public class InventoryItemLabelUpdated implements Event{

	private boolean success;

	public InventoryItemLabelUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}