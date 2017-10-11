package com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.model.InventoryItemTypeAttr;
public class InventoryItemTypeAttrUpdated implements Event{

	private boolean success;

	public InventoryItemTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
