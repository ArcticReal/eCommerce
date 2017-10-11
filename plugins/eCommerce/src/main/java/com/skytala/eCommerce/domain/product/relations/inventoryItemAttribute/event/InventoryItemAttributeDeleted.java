package com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.model.InventoryItemAttribute;
public class InventoryItemAttributeDeleted implements Event{

	private boolean success;

	public InventoryItemAttributeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
