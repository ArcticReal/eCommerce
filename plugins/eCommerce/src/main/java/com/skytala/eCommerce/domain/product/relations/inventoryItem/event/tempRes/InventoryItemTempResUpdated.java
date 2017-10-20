package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
public class InventoryItemTempResUpdated implements Event{

	private boolean success;

	public InventoryItemTempResUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
