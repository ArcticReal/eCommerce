package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.detail;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.detail.InventoryItemDetail;
public class InventoryItemDetailAdded implements Event{

	private InventoryItemDetail addedInventoryItemDetail;
	private boolean success;

	public InventoryItemDetailAdded(InventoryItemDetail addedInventoryItemDetail, boolean success){
		this.addedInventoryItemDetail = addedInventoryItemDetail;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemDetail getAddedInventoryItemDetail() {
		return addedInventoryItemDetail;
	}

}
