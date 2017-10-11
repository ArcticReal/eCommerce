package com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.model.InventoryItemLabel;
public class InventoryItemLabelFound implements Event{

	private List<InventoryItemLabel> inventoryItemLabels;

	public InventoryItemLabelFound(List<InventoryItemLabel> inventoryItemLabels) {
		this.inventoryItemLabels = inventoryItemLabels;
	}

	public List<InventoryItemLabel> getInventoryItemLabels()	{
		return inventoryItemLabels;
	}

}
