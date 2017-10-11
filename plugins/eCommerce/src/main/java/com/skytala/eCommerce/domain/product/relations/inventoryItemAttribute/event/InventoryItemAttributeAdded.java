package com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.model.InventoryItemAttribute;
public class InventoryItemAttributeAdded implements Event{

	private InventoryItemAttribute addedInventoryItemAttribute;
	private boolean success;

	public InventoryItemAttributeAdded(InventoryItemAttribute addedInventoryItemAttribute, boolean success){
		this.addedInventoryItemAttribute = addedInventoryItemAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public InventoryItemAttribute getAddedInventoryItemAttribute() {
		return addedInventoryItemAttribute;
	}

}
