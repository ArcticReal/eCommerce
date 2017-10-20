package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.label;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.label.InventoryItemLabel;
public class InventoryItemLabelFound implements Event{

	private List<InventoryItemLabel> inventoryItemLabels;

	public InventoryItemLabelFound(List<InventoryItemLabel> inventoryItemLabels) {
		this.inventoryItemLabels = inventoryItemLabels;
	}

	public List<InventoryItemLabel> getInventoryItemLabels()	{
		return inventoryItemLabels;
	}

}
