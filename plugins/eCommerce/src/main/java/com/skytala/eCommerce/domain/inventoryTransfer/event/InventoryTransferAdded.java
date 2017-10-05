package com.skytala.eCommerce.domain.inventoryTransfer.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryTransfer.model.InventoryTransfer;
public class InventoryTransferAdded implements Event{

	private InventoryTransfer addedInventoryTransfer;
	private boolean success;

	public InventoryTransferAdded(InventoryTransfer addedInventoryTransfer, boolean success){
		this.addedInventoryTransfer = addedInventoryTransfer;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryTransfer getAddedInventoryTransfer() {
		return addedInventoryTransfer;
	}

}
