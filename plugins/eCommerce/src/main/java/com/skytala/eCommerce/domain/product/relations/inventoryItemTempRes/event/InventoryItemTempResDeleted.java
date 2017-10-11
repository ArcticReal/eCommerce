package com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.model.InventoryItemTempRes;
public class InventoryItemTempResDeleted implements Event{

	private boolean success;

	public InventoryItemTempResDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
