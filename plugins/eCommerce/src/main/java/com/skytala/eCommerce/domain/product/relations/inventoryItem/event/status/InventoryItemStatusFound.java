package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.status;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.status.InventoryItemStatus;
public class InventoryItemStatusFound implements Event{

	private List<InventoryItemStatus> inventoryItemStatuss;

	public InventoryItemStatusFound(List<InventoryItemStatus> inventoryItemStatuss) {
		this.inventoryItemStatuss = inventoryItemStatuss;
	}

	public List<InventoryItemStatus> getInventoryItemStatuss()	{
		return inventoryItemStatuss;
	}

}
