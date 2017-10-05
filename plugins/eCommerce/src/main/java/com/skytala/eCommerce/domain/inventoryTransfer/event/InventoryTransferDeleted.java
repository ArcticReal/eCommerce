package com.skytala.eCommerce.domain.inventoryTransfer.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryTransfer.model.InventoryTransfer;
public class InventoryTransferDeleted implements Event{

	private boolean success;

	public InventoryTransferDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
