package com.skytala.eCommerce.domain.inventoryItemLabel.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItemLabel.model.InventoryItemLabel;
public class InventoryItemLabelDeleted implements Event{

	private boolean success;

	public InventoryItemLabelDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
