package com.skytala.eCommerce.domain.inventoryTransfer.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryTransfer.model.InventoryTransfer;
public class InventoryTransferUpdated implements Event{

	private boolean success;

	public InventoryTransferUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}