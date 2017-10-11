package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
public class InventoryItemVarianceDeleted implements Event{

	private boolean success;

	public InventoryItemVarianceDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
