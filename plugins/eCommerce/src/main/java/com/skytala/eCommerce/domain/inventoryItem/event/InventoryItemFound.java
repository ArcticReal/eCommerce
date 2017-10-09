package com.skytala.eCommerce.domain.inventoryItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.inventoryItem.model.InventoryItem;
public class InventoryItemFound implements Event{

	private List<InventoryItem> inventoryItems;

	public InventoryItemFound(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public List<InventoryItem> getInventoryItems()	{
		return inventoryItems;
	}

}