package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
public class InventoryItemVarianceFound implements Event{

	private List<InventoryItemVariance> inventoryItemVariances;

	public InventoryItemVarianceFound(List<InventoryItemVariance> inventoryItemVariances) {
		this.inventoryItemVariances = inventoryItemVariances;
	}

	public List<InventoryItemVariance> getInventoryItemVariances()	{
		return inventoryItemVariances;
	}

}
