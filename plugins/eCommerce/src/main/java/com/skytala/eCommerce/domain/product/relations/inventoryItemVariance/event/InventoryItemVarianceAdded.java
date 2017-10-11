package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
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
