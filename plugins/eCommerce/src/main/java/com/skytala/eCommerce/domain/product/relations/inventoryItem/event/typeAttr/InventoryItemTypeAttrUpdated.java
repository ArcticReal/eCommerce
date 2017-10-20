package com.skytala.eCommerce.domain.product.relations.inventoryItem.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.typeAttr.InventoryItemTypeAttr;
public class InventoryItemTypeAttrUpdated implements Event{

	private boolean success;

	public InventoryItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
