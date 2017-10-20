package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
public class InventoryItemTempResAdded implements Event{

	private InventoryItemTempRes addedInventoryItemTempRes;
	private boolean success;

	public InventoryItemTempResAdded(InventoryItemTempRes addedInventoryItemTempRes, boolean success){
		this.addedInventoryItemTempRes = addedInventoryItemTempRes;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemTempRes getAddedInventoryItemTempRes() {
		return addedInventoryItemTempRes;
	}

}
