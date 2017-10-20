package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.type.InventoryItemType;
public class InventoryItemTypeFound implements Event{

	private List<InventoryItemType> inventoryItemTypes;

	public InventoryItemTypeFound(List<InventoryItemType> inventoryItemTypes) {
		this.inventoryItemTypes = inventoryItemTypes;
	}

	public List<InventoryItemType> getInventoryItemTypes()	{
		return inventoryItemTypes;
	}

}
