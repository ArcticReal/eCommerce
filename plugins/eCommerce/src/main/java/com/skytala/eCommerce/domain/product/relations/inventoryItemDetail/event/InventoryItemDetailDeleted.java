package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model.InventoryItemDetail;
public class InventoryItemDetailDeleted implements Event{

	private boolean success;

	public InventoryItemDetailDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
