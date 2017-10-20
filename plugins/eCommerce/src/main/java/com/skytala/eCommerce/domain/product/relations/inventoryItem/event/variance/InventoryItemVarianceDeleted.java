package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance.InventoryItemVariance;
public class InventoryItemVarianceDeleted implements Event{

	private boolean success;

	public InventoryItemVarianceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
