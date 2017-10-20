package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance.InventoryItemVariance;
public class InventoryItemVarianceAdded implements Event{

	private InventoryItemVariance addedInventoryItemVariance;
	private boolean success;

	public InventoryItemVarianceAdded(InventoryItemVariance addedInventoryItemVariance, boolean success){
		this.addedInventoryItemVariance = addedInventoryItemVariance;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemVariance getAddedInventoryItemVariance() {
		return addedInventoryItemVariance;
	}

}
