package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
public class InventoryItemTempResFound implements Event{

	private List<InventoryItemTempRes> inventoryItemTempRess;

	public InventoryItemTempResFound(List<InventoryItemTempRes> inventoryItemTempRess) {
		this.inventoryItemTempRess = inventoryItemTempRess;
	}

	public List<InventoryItemTempRes> getInventoryItemTempRess()	{
		return inventoryItemTempRess;
	}

}
