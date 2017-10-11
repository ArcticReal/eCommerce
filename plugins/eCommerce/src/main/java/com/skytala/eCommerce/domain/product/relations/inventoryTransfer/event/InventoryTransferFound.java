package com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model.InventoryTransfer;
public class InventoryTransferFound implements Event{

	private List<InventoryTransfer> inventoryTransfers;

	public InventoryTransferFound(List<InventoryTransfer> inventoryTransfers) {
		this.inventoryTransfers = inventoryTransfers;
	}

	public List<InventoryTransfer> getInventoryTransfers()	{
		return inventoryTransfers;
	}

}
