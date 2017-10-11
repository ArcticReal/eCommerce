package com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemTempRes.model.InventoryItemTempRes;
public class InventoryItemTempResFound implements Event{

	private List<InventoryItemTempRes> inventoryItemTempRess;

	public InventoryItemTempResFound(List<InventoryItemTempRes> inventoryItemTempRess) {
		this.inventoryItemTempRess = inventoryItemTempRess;
	}

	public List<InventoryItemTempRes> getInventoryItemTempRess()	{
		return inventoryItemTempRess;
	}

}
