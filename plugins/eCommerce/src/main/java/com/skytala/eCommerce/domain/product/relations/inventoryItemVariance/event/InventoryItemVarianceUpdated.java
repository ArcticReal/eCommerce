package com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemVariance.model.InventoryItemVariance;
public class InventoryItemVarianceUpdated implements Event{

	private boolean success;

	public InventoryItemVarianceUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
