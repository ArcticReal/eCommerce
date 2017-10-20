package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance.InventoryItemVariance;
public class InventoryItemVarianceFound implements Event{

	private List<InventoryItemVariance> inventoryItemVariances;

	public InventoryItemVarianceFound(List<InventoryItemVariance> inventoryItemVariances) {
		this.inventoryItemVariances = inventoryItemVariances;
	}

	public List<InventoryItemVariance> getInventoryItemVariances()	{
		return inventoryItemVariances;
	}

}
