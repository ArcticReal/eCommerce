package com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.model.InventoryItemStatus;
public class InventoryItemStatusFound implements Event{

	private List<InventoryItemStatus> inventoryItemStatuss;

	public InventoryItemStatusFound(List<InventoryItemStatus> inventoryItemStatuss) {
		this.inventoryItemStatuss = inventoryItemStatuss;
	}

	public List<InventoryItemStatus> getInventoryItemStatuss()	{
		return inventoryItemStatuss;
	}

}
