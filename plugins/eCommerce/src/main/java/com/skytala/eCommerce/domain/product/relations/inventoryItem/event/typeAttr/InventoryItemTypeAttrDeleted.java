package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
public class InventoryItemTypeAttrDeleted implements Event{

	private boolean success;

	public InventoryItemTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
